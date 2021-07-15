package studio.rrprojects.runnerbuddy.misc;

import java.util.ArrayList;

public class PriorityGroup {

    private final ArrayList<PriorityOption> listPriorityOptions;
    private final String keyword;
    private PriorityOption selectedOption;

    public PriorityGroup(String keyword) {
        this.keyword = keyword;
       listPriorityOptions = new ArrayList<>();
       selectedOption = null;
    }

    public void addOption(int optionNumber, String priorityLevel, String displayName) {
        //Used if the priority group doesn't have a corresponding numerical value
        listPriorityOptions.add(new PriorityOption(optionNumber, priorityLevel, displayName, 0));
    }
    public void addOption(int optionNumber, String priorityLevel, String displayName, int numValue) {
        listPriorityOptions.add(new PriorityOption(optionNumber, priorityLevel, displayName, numValue));
    }

    public ArrayList<PriorityOption> getListPriorityOptions() {
        return listPriorityOptions;
    }

    public String getKeyword() {
        return keyword;
    }

    public PriorityOption getOptionByNumber(String text) {
        int value = Integer.parseInt(text);
        for (PriorityOption option : listPriorityOptions) {
            if (option.optionNumber == value) {
                return option;
            }
        }
        return null;
    }

    public PriorityOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(PriorityOption selectedOption) {
        this.selectedOption = selectedOption;
    }

    public static class PriorityOption {
        private final String priorityLevel;
        private final String displayName;
        private final int optionNumber;
        private final int numValue;

        public PriorityOption(int optionNumber, String priorityLevel, String displayName, int numValue) {
            this.optionNumber = optionNumber;
            this.priorityLevel = priorityLevel;
            this.displayName = displayName;
            this.numValue = numValue;
        }

        public int getOptionNumber() { return optionNumber; }

        public String getPriorityLevel() {
            return priorityLevel;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getNumValue() {
            return numValue;
        }
    }
}
