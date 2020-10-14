package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.MagicPriorityContainer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MagicController {
    private MageSubtype selectedMageType;
    ArrayList<MagicPriorityContainer> magicPriorityTable;
    private ArrayList<MageSubtype> listMageSubtypes;
    private ArrayList<MageBaseType> listMageBaseTypes;
    private String selectedMagicLevel;
    private ArrayList<String> listTotems;
    private ArrayList<String> listElements;

    public MagicController(CharacterContainer characterContainer){
        selectedMagicLevel = "Mundane";
        setSelectedMageType("Mundane");

        LoadMagicTypeRules();
        LoadTables();
    }

    private void LoadMagicTypeRules() {
        listMageSubtypes = new ArrayList<>();

        listMageSubtypes.add(new MageSubtype("Hermetic Mage"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Base")
                , new MagicRule("Spell Type", "Base")
                , new MagicRule("Spirit", "Elemental")));

        listMageSubtypes.add(new MageSubtype("Shamanic Mage"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Base")
                , new MagicRule("Spell Type", "Base")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spirit", "Nature")));

        listMageSubtypes.add(new MageSubtype("Conjurer (Hermetic)"
                , new MagicRule("Skill", "Conjuring")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Spirit", "Elemental")));

        listMageSubtypes.add(new MageSubtype("Conjurer (Shamanic)"
                , new MagicRule("Skill", "Conjuring")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spirit", "Nature")));

        listMageSubtypes.add(new MageSubtype("Elementalist"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Element", "Choose")
                , new MagicRule("Spell Type", "Element")
                , new MagicRule("Spirit", "Element")));

        listMageSubtypes.add(new MageSubtype("Shamanist"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spell Type", "Totem")
                , new MagicRule("Spirit", "Totem")));

        listMageSubtypes.add(new MageSubtype("Sorcerer (Hermetic)"
                , new MagicRule("Skill", "Sorcery")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Spell Type", "Base")));

        listMageSubtypes.add(new MageSubtype("Sorcerer (Shamanic)"
                , new MagicRule("Skill", "Sorcery")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spell Type", "Base")));

        listMageSubtypes.add(new MageSubtype("Way of the Adept"
                , new MagicRule("Spell Type", "Adept Powers")));

        listMageSubtypes.add(new MageSubtype("Mundane"));

        listMageBaseTypes = new ArrayList<>();

        listMageBaseTypes.add(new MageBaseType("Full Magician", "Hermetic Mage", "Shamanic Mage"));
        listMageBaseTypes.add(new MageBaseType("Aspected Magician", "Conjurer (Hermetic)", "Conjurer (Shamanic)", "Elementalist", "Shamanist", "Sorcerer (Hermetic)", "Sorcerer (Shamanic)"));
        listMageBaseTypes.add(new MageBaseType("Adept", "Way of the Adept"));
        listMageBaseTypes.add(new MageBaseType("Mundane", "Mundane"));

        listTotems = new ArrayList<>();
        listTotems.addAll(Arrays.asList("Bear", "Buffalo", "Cat", "Coyote", "Dog", "Dolphin", "Eagle", "Gator", "Lion", "Mouse", "Owl", "Raccoon", "Rat", "Raven", "Shark", "Snake", "Wolf"));

        listElements = new ArrayList<>();
        listElements.addAll(Arrays.asList("Earth", "Fire", "Air", "Water"));

    }

    private void LoadTables() {
        magicPriorityTable = new ArrayList<MagicPriorityContainer>();
        magicPriorityTable.add(new MagicPriorityContainer("A", new String[]{"Full Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("B", new String[]{"Adept", "Aspected Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("C", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("D", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("E", new String[]{"Mundane"}));
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Magic Level --");
        for (MagicPriorityContainer magic: magicPriorityTable) {
            box.addElement(String.format("%s - %s", magic.getPriorityLevel(), magic.GetListAsString()));
        }
        return box;
    }

    public MagicPriorityContainer SearchSubMenu(String input) {
        String searchTerm = String.valueOf(input.charAt(0));
        for (MagicPriorityContainer magic: magicPriorityTable) {
            if (magic.getPriorityLevel().equalsIgnoreCase(searchTerm)) {
                return magic;
            }
        }
        return null;
    }

    public ComboBoxModel<String> getMageTypeBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (MageBaseType mageType: listMageBaseTypes) {
            if (mageType.name.equalsIgnoreCase(selectedMagicLevel)) {
                for (String subtype: mageType.listSubtypes) {
                    model.addElement(subtype);
                }
            }
        }

        return model;
    }

    public ComboBoxModel<String> getTotemBox() {
        System.out.println("POPULATING TOTEM BOX");
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        System.out.println("Selected Mage: " + selectedMageType.name);
        System.out.println("HasChoose? " + selectedMageType.hasChoose());

        if (selectedMageType.hasChoose()) {
            ArrayList<String> list = getChooseList(selectedMageType.getRuleChoose());

            for (String item: list) {
                model.addElement(item);
            }

        }

        return model;
    }

    private ArrayList<String> getChooseList(MagicRule rule) {

        if (rule.category.equalsIgnoreCase("Totem")) {
            return listTotems;
        } else {
            return listElements;
        }

    }

    public void setSelectedMagicLevel(String searchTerm) {
        if (searchTerm.startsWith("--")) {
            selectedMagicLevel = "Mundane";
        } else {
            selectedMagicLevel = searchTerm;
        }
    }

    public void setSelectedMageType(String searchTerm) {
        try {
            for (MageSubtype type: listMageSubtypes) {
                if (type.name.equalsIgnoreCase(searchTerm)) {
                    selectedMageType = type;
                }
            }
        } catch (NullPointerException e) {
            selectedMageType = new MageSubtype("Mundane");
        }


    }


    private class MageBaseType {
        private final String name;
        private final ArrayList<String> listSubtypes;

        public MageBaseType(String name, String... categories) {
            this.name = name;
            listSubtypes = new ArrayList<>();
            listSubtypes.addAll(Arrays.asList(categories));
        }
    }

    private class MageSubtype {
        private final ArrayList<MagicRule> listRules;
        private final String name;
        private boolean hasChoose;
        private MagicRule ruleChoose;

        public MageSubtype(String name, MagicRule... rules){
            this.name = name;
            hasChoose = false;
            ruleChoose = null;
            listRules = new ArrayList<>();
            listRules.addAll(Arrays.asList(rules));

            setChoose();

        }

        private void setChoose() {
            for (MagicRule rule: listRules) {
                if (rule.ruleType.equalsIgnoreCase("Choose"))  {
                    hasChoose = true;
                    ruleChoose = rule;
                    return;
                }
            }

            hasChoose = false;
        }

        public ArrayList<MagicRule> getListRules() {
            return listRules;
        }

        public String getName() {
            return name;
        }

        public boolean hasChoose() {
            return hasChoose;
        }

        public MagicRule getRuleChoose() {
            return ruleChoose;
        }
    }

    private class MagicRule {
        private final String category;
        private final String ruleType;

        public MagicRule(String category, String ruleType) {
            this.category = category;
            this.ruleType = ruleType;
        }

        public String getCategory() {
            return category;
        }

        public String getRuleType() {
            return ruleType;
        }
    }
}
