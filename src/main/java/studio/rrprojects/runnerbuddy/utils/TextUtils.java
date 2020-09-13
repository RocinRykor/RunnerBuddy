package studio.rrprojects.runnerbuddy.utils;

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
}
