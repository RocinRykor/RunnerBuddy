package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.magic.MagicPriorityContainer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MagicController {
    ArrayList<MagicPriorityContainer> magicPriorityTable;
    private LinkedHashMap<Object, Object> tableMagicType;

    public MagicController(){

        LoadMagicTypeRules();
        LoadTables();
    }

    private void LoadMagicTypeRules() {
        new MagicSubtypeRuleSet("Hermetic Mage"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Base")
                , new MagicRule("Spell Type", "Base")
                , new MagicRule("Spirit", "Elemental"));

        new MagicSubtypeRuleSet("Shamanic Mage"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Base")
                , new MagicRule("Spell Type", "Base")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spirit", "Nature"));

        new MagicSubtypeRuleSet("Conjurer (Hermetic)"
                , new MagicRule("Skill", "Conjuring")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Spirit", "Elemental"));

        new MagicSubtypeRuleSet("Conjurer (Shamanic)"
                , new MagicRule("Skill", "Conjuring")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spirit", "Nature"));

        new MagicSubtypeRuleSet("Elementalist"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Element", "Choose")
                , new MagicRule("Spell Type", "Element")
                , new MagicRule("Spirit", "Element"));

        new MagicSubtypeRuleSet("Shamanist"
                , new MagicRule("Skill", "Base")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spell Type", "Totem")
                , new MagicRule("Spirit", "Totem"));

        new MagicSubtypeRuleSet("Sorcerer (Hermetic)"
                , new MagicRule("Skill", "Sorcery")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Spell Type", "Base"));

        new MagicSubtypeRuleSet("Sorcerer (Shamanic)"
                , new MagicRule("Skill", "Sorcery")
                , new MagicRule("Astral", "Perception")
                , new MagicRule("Totem", "Choose")
                , new MagicRule("Spell Type", "Base"));

        new MagicSubtypeRuleSet("Way of the Adept"
                , new MagicRule("Spell Type", "Adept Powers"));

        new MagicRuleSet("Full Magician", "Hermetic Mage", "Shamanic Mage");
        new MagicRuleSet("Aspected Magician", "Conjurer (Hermetic)", "Conjurer (Shamanic)", "Elementalist", "Shamanist", "Sorcerer (Hermetic)", "Sorcerer (Shamanic)");
        new MagicRuleSet("Adept", "Way of the Adept");
        new MagicRuleSet("Mundane", "None");


    }

    private void LoadTables() {
        magicPriorityTable = new ArrayList<MagicPriorityContainer>();
        magicPriorityTable.add(new MagicPriorityContainer("A", new String[]{"Full Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("B", new String[]{"Adept", "Aspected Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("C", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("D", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("E", new String[]{"Mundane"}));

        tableMagicType = new LinkedHashMap<>();
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

    private class MagicRuleSet {
        public MagicRuleSet(String baseType, String... categories) {
        }
    }

    private class MagicSubtypeRuleSet {
        public MagicSubtypeRuleSet(String baseType, MagicRule... allowed){

        }
    }

    private class MagicRule {
        public MagicRule(String category, String ruleType) {
        }
    }
}
