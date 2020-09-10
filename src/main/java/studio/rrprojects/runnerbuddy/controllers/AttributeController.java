package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.attributes.AttributePriorityContainer;
import studio.rrprojects.runnerbuddy.gui.cards.AttributeCard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeController {
    LinkedHashMap<String, AttributePriorityContainer> getPriorityTableAttributes;
    HashMap<String, AttributeArray> racialModTable;
    private AttributePriorityContainer selectedAttributePriority;
    private AttributeArray selectedAttributes;

    public AttributeController() {
        selectedAttributePriority = new AttributePriorityContainer("BLANK", 6);
        selectedAttributes = new AttributeArray(1, 1, 1, 1, 1, 1);
        PopTables();
    }

    private void PopTables() {
        //Racial Mod Table
        racialModTable = new HashMap<String, AttributeArray>();
        racialModTable.put("Troll", new AttributeArray(5, -1, 4, -2, -2, 0));
        racialModTable.put("Elf", new AttributeArray(0, 1, 0, 2, 0,0));
        racialModTable.put("Dwarf", new AttributeArray(1, 0, 2, 0, 0,1));
        racialModTable.put("Ork", new AttributeArray(3, 2, 0, -1, -1,0));
        racialModTable.put("Human", new AttributeArray(0, 0, 0, 0, 0,0));

        //Attribute Points Priority
        getPriorityTableAttributes = new LinkedHashMap<>();
        getPriorityTableAttributes.put("A", new AttributePriorityContainer("A", 30));
        getPriorityTableAttributes.put("B", new AttributePriorityContainer("B", 27));
        getPriorityTableAttributes.put("C", new AttributePriorityContainer("C", 24));
        getPriorityTableAttributes.put("D", new AttributePriorityContainer("D", 21));
        getPriorityTableAttributes.put("E", new AttributePriorityContainer("E", 18));
    }

    public DefaultComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<String>();
        box.addElement("-- Select Attribute Level --");
        for (Map.Entry<String, AttributePriorityContainer> attribute: getPriorityTableAttributes.entrySet()) {
            box.addElement(String.format("%s - %d Points", attribute.getValue().getPriorityLevel(), attribute.getValue().getAttributePoints()));
        }
        return box;
    }

    public void setSelectedAttributePriority(Object selectedItem) {
        if (selectedItem.toString().startsWith("--")) {
            return;
        }

        String searchTerm = String.valueOf(selectedItem.toString().charAt(0));

        selectedAttributePriority = getPriorityTableAttributes.get(searchTerm);
    }

    public AttributeArray getSelectedAttributes() {
        return selectedAttributes;
    }

    public class AttributeArray {
        private LinkedHashMap<String, Integer> attributeList;

        public AttributeArray(int attrBody, int attrQuickness, int attrStrength, int attrCharisma, int attrIntelligence, int attrWillpower) {
            attributeList = new LinkedHashMap<>();
            attributeList.put("Body", attrBody);
            attributeList.put("Quickness", attrQuickness);
            attributeList.put("Strength", attrStrength);
            attributeList.put("Charisma", attrCharisma);
            attributeList.put("Intelligence", attrIntelligence);
            attributeList.put("Willpower", attrWillpower);

            attributeList.put("Essence", 6);
            attributeList.put("Magic", 0);
            CalcReaction();
        }

        private void CalcReaction() {
            int quickness = attributeList.get("Quickness");
            int intelligence = attributeList.get("Intelligence");;

            int reaction = (int) Math.floor((quickness + intelligence) >> 1);

            if (attributeList.containsKey("Reaction")) {
                attributeList.replace("Reaction", reaction);
            } else {
                attributeList.put("Reaction", reaction);
            }
        }

        public int getAttribute(String attributeName) {
            return attributeList.get(attributeName);
        }

        public void UpdateBaseAttributes(LinkedHashMap<String, Integer> arrayList) {
            for (Map.Entry<String, Integer> attribute: arrayList.entrySet()) {
                attributeList.replace(attribute.getKey(), attribute.getValue());
            }

            CalcReaction();
        }
    }

    public LinkedHashMap<String, AttributePriorityContainer> getPriorityTableAttributes() {
        return getPriorityTableAttributes;
    }

    public HashMap<String, AttributeArray> getRacialModTable() {
        return racialModTable;
    }

    public AttributePriorityContainer getSelectedAttributePriority() {
        return selectedAttributePriority;
    }
}
