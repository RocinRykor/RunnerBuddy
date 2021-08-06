package studio.rrprojects.runnerbuddy.textbuilder;

import java.util.ArrayList;

public class TextBuilder {
    private final ArrayList<TextObject> textObjectList = new ArrayList<>();;

    public String build() {
        System.out.println("BUILDING!");

        StringBuilder output = new StringBuilder();

        for (TextObject textObject: textObjectList) {
            output.append(textObject.toString(0)).append("\n");
        }

        return output.toString();
    }

    public void add(TextObject textObject) {
        textObjectList.add(textObject);
    }
}
