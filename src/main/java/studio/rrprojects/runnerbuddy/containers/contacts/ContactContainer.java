package studio.rrprojects.runnerbuddy.containers.contacts;

import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.LinkedHashMap;

public class ContactContainer {
    String contactName, contactOccupation, contactLevelBonus, contactNotes;
    int contactLevel, contactCost;
    boolean isFree;
    LinkedHashMap<Integer, String> tableLevelBonus;
    LinkedHashMap<Integer, Integer> tableCost;

    public ContactContainer(String contactName, int contactLevel, boolean isFree) {
        InitTable();
        this.contactName = contactName;
        this.contactLevel = contactLevel;
        this.isFree = isFree;
        contactOccupation = "";
        contactLevelBonus = getContactLevelBonus();
        contactNotes = "";
    }

    private void InitTable() {
        tableLevelBonus = new LinkedHashMap<>();
        tableLevelBonus.put(1, "Acquaintance, no real loyalty. Intelligence (6) Test for remembering information about a runner.");
        tableLevelBonus.put(2, "Buddy, Receive 1 extra dice for Etiquette Tests. Willpower (5) test for refusing to give up runner info");
        tableLevelBonus.put(3, "Friend-For-Life, Receive 2 extra die for Etiquette Tests. Willpower (6) test for refusing to give up runner info.");

        tableCost = new LinkedHashMap<>();
        tableCost.put(1, 5000);
        tableCost.put(2, 10000);
        tableCost.put(3, 200000);
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactOccupation() {
        return contactOccupation;
    }

    public void setContactOccupation(String contactOccupation) {
        this.contactOccupation = contactOccupation;
    }

    public String getContactLevelBonus() {
        String bonus = tableLevelBonus.getOrDefault(contactLevel, "");

        String cost = "";
        if (isFree) {
            cost = "Free Contact";
        } else {
            cost = "Cost: " + TextUtils.IntToCash(contactCost);
        }

        return bonus + " --- " + cost;
    }

    public String getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(String contactNotes) {
        this.contactNotes = contactNotes;
    }

    public int getContactLevel() {
        return contactLevel;
    }

    public void setContactLevel(int contactLevel) {
        this.contactLevel = contactLevel;
        setContactCost();
    }

    private void setContactCost() {
        if (isFree) {
            contactCost = 0;
        } else {
            contactCost = tableCost.get(contactLevel);
        }
    }

    public boolean isFree() {
        return isFree;
    }

    public String StringFormat() {
        return String.format("%s - %s - Level %d", contactName, contactOccupation, contactLevel);
    }
}
