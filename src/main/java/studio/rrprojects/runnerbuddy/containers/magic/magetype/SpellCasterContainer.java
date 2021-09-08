package studio.rrprojects.runnerbuddy.containers.magic.magetype;

import studio.rrprojects.runnerbuddy.constants.MagicConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SpellCasterContainer extends MagicUserContainer{
    private LinkedHashMap<String, ArrayList<String>> traditionOptions = new LinkedHashMap<>();

    public SpellCasterContainer(String mageType, double basePoints) {
        super(mageType);
        setSpellPointsBase(basePoints);

        traditionOptions.put(MagicConstants.HERMETIC, new ArrayList<>());
        traditionOptions.put(MagicConstants.SHAMANIC, new ArrayList<>());
    }

    public void addHermeticOption(String s) {
        traditionOptions.get(MagicConstants.HERMETIC).add(s);
    }

    public void addHermeticOptions(String[] list) {
        for (String s: list) {
            traditionOptions.get(MagicConstants.HERMETIC).add(s);
        }
    }

    public void addShamanicOption(String s) {
        traditionOptions.get(MagicConstants.SHAMANIC).add(s);
    }

    public void addShamanicOptions(String[] list) {
        for (String s: list) {
            traditionOptions.get(MagicConstants.SHAMANIC).add(s);
        }
    }

    public LinkedHashMap<String, ArrayList<String>> getTraditionOptions() {
        return traditionOptions;
    }

    @Override
    public String toString() {
        return getMageType();
    }
}
