package studio.rrprojects.runnerbuddy.utils;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.text.NumberFormat;
import java.util.Locale;

public class TextUtils {
    public static String TitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public static String IntToCash(int input) {
        //¥
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("en_US"));
        return numberFormat.format(input) + "¥";
    }

    public static String PrettyKey(String input) {
        String output = input.replaceAll("_", " ");
        return TitleCase(output);
    }

    public static void CenterPaneText(JTextPane textPane) {
        //I don't know exactly how this works yet, but it does so that's all that matters
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
}
