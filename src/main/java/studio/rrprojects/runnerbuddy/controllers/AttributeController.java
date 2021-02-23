package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Attributes;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;

import java.util.HashMap;

public class AttributeController {

    private PriorityGroup priorityGroup;
    private HashMap<String, Integer> mapAttribute;
    private PriorityGroup.PriorityOption selectedPriorityOption;
    private Attributes attributes;

    public AttributeController(CharacterContainer characterContainer) {

        GeneratePriorityGroup();
        GenerateAttributeMap();
    }

    public void setSelectedPriorityOption(PriorityGroup.PriorityOption priorityOption) {
        selectedPriorityOption = priorityOption;
        attributes.MassUpdateEvent();
    }

    private void GenerateAttributeMap() {
        mapAttribute = new HashMap<>();
        mapAttribute.put("Body", 0);
        mapAttribute.put("Strength", 0);
        mapAttribute.put("Quickness", 0);
        mapAttribute.put("Charisma", 0);
        mapAttribute.put("Intelligence", 0);
        mapAttribute.put("Willpower", 0);
        mapAttribute.put("Essence", 0);
        mapAttribute.put("Magic", 0);
        mapAttribute.put("Reaction", 0);
    }

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Attributes");
        priorityGroup.addOption(1, "A", "30 Points", 30);
        priorityGroup.addOption(2, "B", "27 Points", 27);
        priorityGroup.addOption(3, "C", "24 Points", 24);
        priorityGroup.addOption(4, "D", "21 Points", 21);
        priorityGroup.addOption(5, "E", "18 Points", 18);
    }

    public HashMap<String, Integer> getMapAttribute() {
        return mapAttribute;
    }

    public PriorityGroup.PriorityOption getSelectedPriorityOption() {
        return selectedPriorityOption;
    }

    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public void passThisCard(Attributes attributes) {
        this.attributes = attributes;
    }

    public Attributes getAttributesCard() {
        return attributes;
    }
}
