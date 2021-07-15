package studio.rrprojects.runnerbuddy.containers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

public class SelectedSkillContainer extends SkillContainer {
    private final SkillContainer selectedSkill;
    private int pointCost = 1;

    public SelectedSkillContainer(SkillContainer selectedSkill) {
        this.selectedSkill = selectedSkill;

        setSkillType(selectedSkill.getSkillType());
        setAvailableSpecializations(selectedSkill.getAvailableSpecializations());
        setSelectedSpecializations(selectedSkill.getSelectedSpecializations());
        setAttribute(selectedSkill.getAttribute());
        setSkillLevel(selectedSkill.getSkillLevel());

        System.out.println("Skill Level: " + getSkillLevel());

        setCategory(selectedSkill.getCategory());
        setSkillName(selectedSkill.getSkillName());
        setDescription(selectedSkill.getDescription());
        setSource(selectedSkill.getSource());
    }

    public SkillContainer getSelectedSkill() {
        return selectedSkill;
    }

    public void CaculatePointCost(CharacterContainer characterContainer) {

        System.out.println("RECALCULATE: ");

        String attribute = getAttribute();
        System.out.println("RECALCULATE: " + attribute);

        int attributeLevel = characterContainer.getAttributeController().getAttributeMap().get(attribute).getTotalPoints();
        System.out.println("RECALCULATE: " + attributeLevel);

        int skillLevel = getSkillLevel();
        System.out.println("RECALCULATE: " + skillLevel);

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
}
