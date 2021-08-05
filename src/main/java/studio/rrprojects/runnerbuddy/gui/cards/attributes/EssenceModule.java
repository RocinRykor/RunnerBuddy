package studio.rrprojects.runnerbuddy.gui.cards.attributes;

public class EssenceModule extends AttributeModule {
    double basePoint = 6; //Everyone starts out at 6 Essence
    double totalPoints;

    public EssenceModule() {
        setAllottedPoints(0);
        UpdateValues();
        setEditable(false);
    }

    @Override
    public void UpdateValues() {
        //TODO Calculate Essence loss from bioware/cyberware

        setTotalPoints(basePoint);
        setTotalText(String.valueOf(basePoint));
    }

    private void setTotalPoints(double points) {
        totalPoints = points;
    }

    public double getTotalPointsAsDouble() {
        return totalPoints;
    }
}
