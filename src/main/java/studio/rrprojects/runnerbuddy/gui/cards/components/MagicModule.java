package studio.rrprojects.runnerbuddy.gui.cards.components;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Attributes;

public class MagicModule extends AttributeModule {
    int basePoint = 0;

    public MagicModule(CharacterContainer characterContainer, String string, Attributes attributes) {
        setAllottedPoints(0);
        LinkAttribute(characterContainer, string, attributes);

        UpdateValues();
        setEditable(false);
    }

    @Override
    public void UpdateValues() {
        int magicPoints = basePoint;

        boolean isMagical = false;

        try {
           isMagical = getCharacterContainer().getMagicController().isMagical();
        } catch (NullPointerException ignored) {
            System.out.println("MAGIC MODULE ERROR: NULL");
        }

        if (isMagical) {
            magicPoints = 6;
            double essencePoints = getAttributes().getAttributeMap().get("Essence").getTotalPointsAsDouble();
            magicPoints = (int) Math.floor(essencePoints);
        }

        setTotalPoints(magicPoints);
        setTotalText(String.valueOf(magicPoints));
    }

}
