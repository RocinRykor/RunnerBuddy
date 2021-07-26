package studio.rrprojects.runnerbuddy.constants;

public class MagicConstants {

    //Magic Types  - Options at various priority levels
    public static final String FULL_MAGICIAN = "Full Magician";
    public static final String ASPECTED_MAGICIAN = "Aspected Magician";
    public static final String MAGICIAN_ADEPT = "Magician Adept";
    public static final String ADEPT = "Adept";
    public static final String SPELLCASTER = "Spellcaster"; //putting this here for when I need to check whether the person is an adept or a "normal wizard"

    //Traditions
    public static final String HERMETIC = "Hermetic";
    public static final String MAGE = "Mage"; //Shorthand
    public static final String SHAMAN = "Shaman";

    //Aspects
    public static final String FULL_MAGE = "Full Mage";
    public static final String FULL_SHAMAN = "Full Shaman";

    public static final String ELEMENTALIST = "Elementalist";
    public static final String MAGE_SORCERER = "Mage Sorcerer";
    public static final String MAGE_CONJURER = "Mage Conjurer";

    public static final String SHAMINIST = "Shamanist";
    public static final String SHAMAN_SORCERER = "Shaman Sorcerer";
    public static final String SHAMAN_CONJURER = "Shaman Conjurer";

    //Tags - for checking what is allowed or not at character creation
    public static final String TAG_ASTRAL_PROJECTION = "Astral Projection";
    public static final String TAG_ASTRAL_PERCEPTION = "Astral Perception";

    public static final String TAG_SORCERY = "Sorcery";
    public static final String TAG_CONJURING = "Conjuring";

    public static final String TAG_LIMIT_TOTEM = "Limited (Totem)";
    public static final String TAG_LIMIT_ELEMENT = "Limited (Element)";

    //Collections
    public static final String[] COLLECTION_ASPECTS = new String[]{ELEMENTALIST, SHAMINIST, SHAMAN_CONJURER, SHAMAN_SORCERER, MAGE_CONJURER, MAGE_SORCERER};

    public static final String[] COLLECTION_FULL = new String[]{TAG_ASTRAL_PROJECTION, TAG_ASTRAL_PERCEPTION, TAG_SORCERY, TAG_CONJURING};

    public static final String[] COLLECTION_SHAMANIST = new String[]{TAG_ASTRAL_PERCEPTION, TAG_SORCERY + TAG_LIMIT_TOTEM, TAG_CONJURING + TAG_LIMIT_TOTEM};
    public static final String[] COLLECTION_SHAMAN_SORCERER = new String[]{TAG_ASTRAL_PERCEPTION, TAG_SORCERY};
    public static final String[] COLLECTION_SHAMAN_CONJURER = new String[]{TAG_ASTRAL_PERCEPTION, TAG_CONJURING};

    public static final String[] COLLECTION_ELEMENTALIST = new String[]{TAG_ASTRAL_PERCEPTION, TAG_SORCERY + TAG_LIMIT_ELEMENT, TAG_CONJURING + TAG_LIMIT_ELEMENT};
    public static final String[] COLLECTION_MAGE_SORCERER = new String[]{TAG_ASTRAL_PERCEPTION, TAG_SORCERY};
    public static final String[] COLLECTION_MAGE_CONJURER = new String[]{TAG_ASTRAL_PERCEPTION, TAG_CONJURING};
}
