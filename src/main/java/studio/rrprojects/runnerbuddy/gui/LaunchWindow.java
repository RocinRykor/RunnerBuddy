package studio.rrprojects.runnerbuddy.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.constants.ProgramInfoConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;
import studio.rrprojects.runnerbuddy.utils.JUtils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class LaunchWindow extends JFrame {
    private JPanel panelMain;
    private JButton buttonNewCharacter;
    private JButton buttonRandom;
    private JButton buttonLoad;
    private JTextPane textPaneIntro;
    private ArrayList<JButton> listButtons;
    private static final String title = ProgramInfoConstants.PROGRAM_NAME + " " + ProgramInfoConstants.CURRENT_VERSION;
    private CharacterContainer characterContainer;
    private NewCharacterPriorityPopup priorityPopup;

    public LaunchWindow() {
        super(title);

        BeginInit();

        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        buttonNewCharacter.addActionListener(actionEvent -> CreateNewCharacter(false));

        buttonRandom.addActionListener(actionEvent -> CreateNewCharacter(true));
    }

    private void CreateNewCharacter(boolean isRandomCharacter) {
        //Create a new CharacterContainer for use going forward.
        characterContainer = new CharacterContainer();

        //Priority Selection Popup Window
        priorityPopup = new NewCharacterPriorityPopup(characterContainer);

        if (isRandomCharacter) {
            priorityPopup.RandomizeCharacter();
        }

        this.dispose();
    }

    private void BeginInit() {

        //Post Launch cleanup and organizing
        CreateButtonArrayList();
        SetColors();
        CenterTextPane();

        //Currently Disable the buttons that don't function
        //Will remove when functionality is added
        DisableButtons();
    }

    private void DisableButtons() {
        String tooltipText = "Sorry, this function isn't available yet";

        buttonLoad.setEnabled(false);
        buttonLoad.setToolTipText(tooltipText);
    }

    private void CreateButtonArrayList() {
        listButtons = new ArrayList<>();
        listButtons.add(buttonNewCharacter);
        listButtons.add(buttonRandom);
        listButtons.add(buttonLoad);
    }

    private void CenterTextPane() {
        //I don't know exactly how this works yet, but it does so that's all that matters
        StyledDocument doc = textPaneIntro.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    private void SetColors() {
        //Set panel colors
        JUtils.SetDefaultPanelColors(panelMain);

        //Set button colors
        for (JButton button : listButtons) {
            JUtils.SetDefaultButtonFont(button, 18);
            JUtils.SetDefaultButtonColors(button);
        }

        //TextPane
        JUtils.SetDefaultTextPaneColors(textPaneIntro);
        JUtils.SetDefaultTextPaneFont(textPaneIntro, 26);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-14079180));
        panelMain.setFocusCycleRoot(true);
        panelMain.setForeground(new Color(-11805347));
        panelMain.setMinimumSize(new Dimension(800, 600));
        panelMain.setPreferredSize(new Dimension(800, 600));
        buttonNewCharacter = new JButton();
        buttonNewCharacter.setText("CREATE NEW CHARACTER");
        panelMain.add(buttonNewCharacter, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(2, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(2, 2, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonRandom = new JButton();
        buttonRandom.setText("CREATE RANDOM CHARACTER");
        panelMain.add(buttonRandom, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonLoad = new JButton();
        buttonLoad.setText("LOAD EXISTING CHARACTER");
        panelMain.add(buttonLoad, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textPaneIntro = new JTextPane();
        textPaneIntro.setAutoscrolls(false);
        textPaneIntro.setBackground(new Color(-14079180));
        textPaneIntro.setEditable(false);
        textPaneIntro.setEnabled(true);
        Font textPaneIntroFont = this.$$$getFont$$$("Shadowrun", Font.PLAIN, 26, textPaneIntro.getFont());
        if (textPaneIntroFont != null) textPaneIntro.setFont(textPaneIntroFont);
        textPaneIntro.setForeground(new Color(-11805347));
        textPaneIntro.setOpaque(true);
        textPaneIntro.setSelectedTextColor(new Color(-1));
        textPaneIntro.setText("Hello and welcome to RunnerBuddy, a Shadowrun 3rd Edition character creator and manager.\n\nTo get started, please select and option below.");
        panelMain.add(textPaneIntro, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelMain.add(spacer6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
