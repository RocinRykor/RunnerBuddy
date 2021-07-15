package studio.rrprojects.runnerbuddy.containers;

public class SelectedSkillContainer extends SkillContainer {
    private final SkillContainer selectedSkill;

    public SelectedSkillContainer(SkillContainer selectedSkill) {
        this.selectedSkill = selectedSkill;

        setSkillType(selectedSkill.getSkillType());
        setAvailableSpecializations(selectedSkill.getAvailableSpecializations());
        setSelectedSpecializations(selectedSkill.getSelectedSpecializations());
        setAttribute(selectedSkill.getAttribute());
        setSkillLevel(selectedSkill.getSkillLevel());
        setCategory(selectedSkill.getCategory());
        setSkillName(selectedSkill.getSkillName());
        setDescription(selectedSkill.getDescription());
        setSource(selectedSkill.getSource());
    }

    public SkillContainer getSelectedSkill() {
        return selectedSkill;
    }
}
