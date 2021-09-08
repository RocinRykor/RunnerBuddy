package studio.rrprojects.runnerbuddy.containers.magic.magetype;

public class MagicUserContainer {
    private final String mageType;
    double spellPointsBase;
    double adeptPointsBase;

    public MagicUserContainer(String mageType) {
        this.mageType = mageType;
    }

    public double getSpellPointsBase() {
        return spellPointsBase;
    }

    public void setSpellPointsBase(double spellPointsBase) {
        this.spellPointsBase = spellPointsBase;
    }

    public double getAdeptPointsBase() {
        return adeptPointsBase;
    }

    public void setAdeptPointsBase(double adeptPointsBase) {
        this.adeptPointsBase = adeptPointsBase;
    }

    public String getMageType() {
        return mageType;
    }
}
