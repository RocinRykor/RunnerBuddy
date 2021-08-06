package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.textbuilder.TextBuilder;
import studio.rrprojects.runnerbuddy.textbuilder.TextObject;

public class DescriptionController extends ControllerClass {
    private final CharacterContainer characterContainer;
    String nameStreet, nameReal, age, gender, notes, height, weight, hair, eyes, skin;

    public DescriptionController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public String getNameReal() {
        return nameReal;
    }

    public void setNameReal(String nameReal) {
        this.nameReal = nameReal;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String ValidCheck() {
        if (nameReal == null || nameStreet == null) {
            return "Error - Need a Real Name and Street Name.";
        }

        if (nameReal.equalsIgnoreCase("") || nameStreet.equalsIgnoreCase("")) {
            return "Error - Need a Real Name and Street Name.";
        }

        return "Valid";
    }

    @Override
    public void toTextObject(TextBuilder textBuilder) {
        super.toTextObject(textBuilder);
        String names = nameReal + ", a.ka. " + nameStreet;
        String basicDescription = gender + ", " + age;

        TextObject object = new TextObject("=== Description ===");
        object.add(names);
        object.add(basicDescription);

        textBuilder.add(object);
    }
}
