package studio.rrprojects.runnerbuddy.controllers.gear;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.containers.gear.MeleeWeapon;
import studio.rrprojects.runnerbuddy.containers.gear.RangedWeapon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController{
    private final CharacterContainer characterContainer;
    ArrayList<GearContainer> weaponList;

    public WeaponController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        weaponList = new ArrayList<>();
        AddMeleeWeapon("Personal Weapons", "SR3E_personal_weapons");
        AddRangedWeapon("Impact Projectile Weapons", "SR3E_impact_projectile_weapons");
        AddRangedWeapon("Firearms (Small & Medium)", "SR3E_firearms");
        AddRangedWeapon("Firearms (Heavy)", "SR3E_heavy_weapons");
        AddRangedWeapon("Grenades", "SR3E_grenades");
    }

    private void AddRangedWeapon(String subcategory, String fileName) {
    }

    private void AddMeleeWeapon(String subcategory, String fileName) {
    }

}
