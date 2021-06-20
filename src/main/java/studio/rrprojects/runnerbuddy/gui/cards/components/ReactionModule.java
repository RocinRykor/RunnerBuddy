package studio.rrprojects.runnerbuddy.gui.cards.components;

import studio.rrprojects.runnerbuddy.gui.cards.Attributes;

public class ReactionModule extends AttributeModule {

    public ReactionModule() {
        setAllottedPoints(0);
        UpdateValues();
        setEditable(false);
    }

    @Override
    public void UpdateValues() {
        Attributes attrubuteCard = super.getAttributes();
        int quicknessMod = 1;
        int intelligenceMod = 1;


        try {
            quicknessMod = attrubuteCard.getAttributeMap().get("Quickness").getTotalPoints();
            intelligenceMod = attrubuteCard.getAttributeMap().get("Intelligence").getTotalPoints();
        } catch (NullPointerException ignored) {
        }

        int combinedScore = (quicknessMod + intelligenceMod);
        Double halfScore = (double) combinedScore / 2;
        int totalScore = (int) Math.floor(halfScore);

        setTotalPoints(totalScore);
        setTotalText(String.valueOf(totalScore));
    }
}
