package studio.rrprojects.runnerbuddy.textbuilder;

import java.util.ArrayList;

public class TextObject {

    ArrayList<TextObject> textElementList = new ArrayList<>();

    String title;
    private final boolean newLine;
    int baseIndentSpacing = 2;

    public TextObject(String title, boolean newLine) {
        this.title = title;
        this.newLine = newLine;
    }

    public TextObject(String title) {
        this.title = title;
        newLine = false;
    }

    public void add(String s) {
        textElementList.add(new TextObject(s));
    }

    public void add(String s, boolean newLine) {
        textElementList.add(new TextObject(s, newLine));
    }

    public void add(TextObject obj) {
        textElementList.add(obj);
    }

    public String toString(int newIndentSpacing) {
        StringBuilder string = new StringBuilder(title + "\n");
        int totalIndentSpacingCount = baseIndentSpacing + newIndentSpacing;
        String indentString = getIndentString(totalIndentSpacingCount);

        for (TextObject element: textElementList) {
            string.append(indentString).append(element.toString(totalIndentSpacingCount));
            if (newLine) {
                string.append("\n");
            }
        }

        return string.toString();
    }

    private String getIndentString(int indentSpacingCount) {
        return " ".repeat(Math.max(0, indentSpacingCount));
    }

}
