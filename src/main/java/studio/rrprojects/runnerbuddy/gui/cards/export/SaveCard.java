package studio.rrprojects.runnerbuddy.gui.cards.export;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Card;
import studio.rrprojects.runnerbuddy.misc.ValidChecker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SaveCard extends Card {
    private JPanel panelMain;
    private JPanel panelValidation;
    private JPanel panelExport;
    private JProgressBar progressBarValidation;
    private JLabel labelValidation;
    private JTextArea textAreaNotes;
    private JButton buttonExportText;
    private JButton buttonExportJSON;
    private JLabel labelExportFile;

    public SaveCard(String title) {
        super(title);
        setPanel(panelMain);
    }

    @Override
    public void Initialize() {
        super.Initialize();
    }

    @Override
    public void Update() {
        CheckValidation();
    }

    private void CheckValidation() {
        ValidChecker validChecker = getCharacterContainer().ValidCheck();

        if (validChecker.isValid()) {
            if (validChecker.isSoftError()) {
                setDisplay(Color.ORANGE, "CAUTION");
                ProcessNotes(validChecker.getSoftErrorNotes());
            } else {
                setDisplay(Color.GREEN, "VALID");
                SetDisplayNotes("VALID");
            }
        } else {
            setDisplay(Color.RED, "INVALID");
            ProcessNotes(validChecker.getHardErrorNotes());
        }

        //setDisplay()
    }

    private void ProcessNotes(ArrayList<String> notes) {
        StringBuilder output = new StringBuilder();

        for (String note : notes) {
            output.append(note).append("\n\n");
        }

        SetDisplayNotes(String.valueOf(output));
    }

    private void SetDisplayNotes(String string) {
        textAreaNotes.setText(string);
    }

    private void setDisplay(Color color, String text) {
        progressBarValidation.setValue(1);
        progressBarValidation.setForeground(color);
        labelValidation.setText(text);
    }

    private void FormatPanels() {
        panelValidation.setBorder(BorderFactory.createTitledBorder("Character Validation"));
        panelExport.setBorder(BorderFactory.createTitledBorder("Export Character to Text File"));
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
        panelMain.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        panelValidation = new JPanel();
        panelValidation.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelValidation, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        progressBarValidation = new JProgressBar();
        progressBarValidation.setMaximum(1);
        progressBarValidation.setMinimum(0);
        progressBarValidation.setStringPainted(false);
        panelValidation.add(progressBarValidation, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelValidation = new JLabel();
        labelValidation.setText("VALIDATION");
        panelValidation.add(labelValidation, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAreaNotes = new JTextArea();
        textAreaNotes.setEditable(false);
        textAreaNotes.setLineWrap(true);
        textAreaNotes.setWrapStyleWord(true);
        panelValidation.add(textAreaNotes, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        panelExport = new JPanel();
        panelExport.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelExport, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonExportText = new JButton();
        buttonExportText.setEnabled(true);
        buttonExportText.setText("TEXT FILE");
        buttonExportText.setToolTipText("Currently Not Enabled");
        panelExport.add(buttonExportText, new GridConstraints(1, 2, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonExportJSON = new JButton();
        buttonExportJSON.setEnabled(true);
        buttonExportJSON.setText("JSON FILE");
        buttonExportJSON.setToolTipText("Currently Not Enabled");
        panelExport.add(buttonExportJSON, new GridConstraints(1, 0, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelExportFile = new JLabel();
        labelExportFile.setText("Export character as:");
        panelExport.add(labelExportFile, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
