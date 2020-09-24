package studio.rrprojects.runnerbuddy.controllers;

public class DescriptionController {

    String nameStreet, nameReal, age, sex, description, race;

    public DescriptionController() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getFinalText() {

        return String.format("Name: %s, AKA: %s\n" +
                "%s %s, Age %s", nameReal, nameStreet, sex, race, age);
    }
}
