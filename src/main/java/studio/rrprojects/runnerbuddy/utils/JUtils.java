package studio.rrprojects.runnerbuddy.utils;

import javax.swing.*;
import java.awt.*;

public class JUtils {
    public static void SetDefaultPanelColors(JPanel panel) {
        panel.setForeground(ColorUtils.getColorForeground());
        panel.setBackground(ColorUtils.getColorBackground());
    }

    public static void SetDefaultButtonColors(JButton button) {
        button.setForeground(ColorUtils.getColorForeground());
        button.setBackground(ColorUtils.getColorBackground());
    }

    public static void SetDefaultButtonFont(JButton button, int size) {
        button.setFont(FontUtils.getFont(size));
    }

    public static void SetDefaultTextPaneColors(JTextPane textPane) {
        textPane.setForeground(ColorUtils.getColorForeground());
        textPane.setBackground(ColorUtils.getColorBackground());
    }

    public static void SetDefaultTextPaneFont(JTextPane textPane, int size) {
        textPane.setFont(FontUtils.getFont(size));
    }

    public static void SetDefaultButtonColorsAndFont(JButton button, int size) {
        SetDefaultButtonColors(button);
        SetDefaultButtonFont(button, size);
    }

    public static void SetDefaultLabelColorsAndFont(JLabel label, int size) {
        SetDefaultLabelColors(label);
        SetDefaultLabelFont(label, size);
    }

    private static void SetDefaultLabelFont(JLabel label, int size) {
        label.setFont(FontUtils.getFont(size));
    }

    private static void SetDefaultLabelColors(JLabel label) {
        label.setForeground(ColorUtils.getColorForeground());
        label.setBackground(ColorUtils.getColorBackground());
    }

    public static void OpenFrameAtMouseLocation(JFrame frame) {
        Point location = MouseInfo.getPointerInfo().getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();
        frame.setLocation(x, y);
    }

    public static void SetButtonColorsAndFont(JButton button, Color color, int size) {
        SetDefaultButtonColors(button);
        button.setForeground(color);
        SetDefaultButtonFont(button, size);
    }
}
