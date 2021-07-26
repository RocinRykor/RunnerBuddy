package studio.rrprojects.runnerbuddy.containers.magic;

import studio.rrprojects.runnerbuddy.constants.MagicConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SpellCasterContainer extends MagicUserContainer{
    private final String magicType;
    private LinkedHashMap<String, ArrayList<String>> traditionOptions = new LinkedHashMap<>();

    public SpellCasterContainer(String magicType, double basePoints) {
        this.magicType = magicType;
        setSpellPointsBase(basePoints);

        traditionOptions.put(MagicConstants.HERMETIC, new ArrayList<>());
        traditionOptions.put(MagicConstants.SHAMAN, new ArrayList<>());
    }

    public void addHermeticOption(String s) {
        traditionOptions.get(MagicConstants.HERMETIC).add(s);
    }

    public void addHermeticOptions(String[] list) {
        for (String s: list) {
            traditionOptions.get(MagicConstants.HERMETIC).add(s);
        }
    }

    public void addShamanOption(String s) {
        traditionOptions.get(MagicConstants.SHAMAN).add(s);
    }

    public void addShamanOptions(String[] list) {
        for (String s: list) {
            traditionOptions.get(MagicConstants.SHAMAN).add(s);
        }
    }

    @Override
    public String toString() {
        return magicType;
    }
}
