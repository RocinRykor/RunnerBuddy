package studio.rrprojects.runnerbuddy.containers.skills;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

public class SelectedSkillContainer extends SkillContainer {
    private final SkillContainer selectedSkill;
    private int pointCost = 1;

    public SelectedSkillContainer(SkillContainer selectedSkill) {
        super(selectedSkill.getSkillName(), selectedSkill.getSkillType());

        this.selectedSkill = selectedSkill;

        setSkillType(selectedSkill.getSkillType());
        setAvailableSpecializations(selectedSkill.getAvailableSpecializations());
        setSelectedSpecializations(selectedSkill.getSelectedSpecializations());
        setLinkedAttribute(selectedSkill.getLinkedAttribute());
        setBaseLevel(selectedSkill.getBaseLevel());
        setActualLevel(selectedSkill.getActualLevel());
        setCategory(selectedSkill.getCategory());
        setSkillName(selectedSkill.getSkillName());
        setDescription(selectedSkill.getDescription());
        setSource(selectedSkill.getSource());
        setSelectedSpecializations(selectedSkill.getSelectedSpecializations());
    }

    public SkillContainer getSelectedSkill() {
        return selectedSkill;
    }

    public void CaculatePointCost(CharacterContainer characterContainer) {

        String linkedAttribute = getLinkedAttribute();

        int attributeLevel = characterContainer.getAttributeController().getAttributeMap().get(linkedAttribute).getTotalPoints();

        int skillLevel = getBaseLevel();

        if (skillLevel <= attributeLevel) {
            pointCost = skillLevel;
        } else {
            int remainder = skillLevel - attributeLevel;
            pointCost = attributeLevel + (remainder * 2);
        }
    }

    public int getPointCost() {
        return pointCost;
    }

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    @Override
    public String toString() {
        return getDisplayString();
    }

    private String getDisplayString() {
        String string = getSkillName() + " " + getActualLevel();

        for (SpecializationContainer specializationContainer: getSelectedSpecializations()) {
            string += ", " + specializationContainer.getName() + " " + specializationContainer.getLevel();
        }

        string += " | Points Spent: " + getPointCost();

        return string;
    }
}
