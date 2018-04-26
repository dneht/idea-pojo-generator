package io.cana.intellij.pojogenerator.view.ui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by vadim on 24.09.16.
 */
public class GeneratorVew {
    private JPanel rootView;
    private JButton generateButton;
    private RSyntaxTextArea textArea;
    private JRadioButton NONERadioButton;
    private JRadioButton jackson2RadioButton;
    private JRadioButton loganSquareRadioButton;
    private JRadioButton GSONRadioButton;
    private JCheckBox rewriteExistingClassesCheckBox;
    private JTextField className;
    private JScrollPane scrollView;
    private JCheckBox useSettersCheckBox;
    private JCheckBox useGettersCheckBox;
    private JRadioButton autoValueGsonButton;
    private JCheckBox kotlinCheckBox;
    private JCheckBox useStringCheckBox;
    private JRadioButton fastJSONRadioButton;
    private JRadioButton Moshi;
    private JTextField objectSuffix;
    private ButtonGroup languageGroup;
    private ButtonGroup typeButtonGroup;

    public JCheckBox getKotlinCheckBox() {
        return kotlinCheckBox;
    }

    public JCheckBox getUseSettersCheckBox() {
        return useSettersCheckBox;
    }

    public JCheckBox getUseGettersCheckBox() {
        return useGettersCheckBox;
    }

    public JPanel getRootView() {
        return rootView;
    }

    public ButtonGroup getTypeButtonGroup() {
        return typeButtonGroup;
    }

    public JTextField getClassNameTextField() {
        return className;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getObjectSuffixTextField() {
        return objectSuffix;
    }

    public JRadioButton getNONERadioButton() {
        return NONERadioButton;
    }

    public JRadioButton getJackson2RadioButton() {
        return jackson2RadioButton;
    }

    public JRadioButton getLoganSquareRadioButton() {
        return loganSquareRadioButton;
    }

    public JRadioButton getGSONRadioButton() {
        return GSONRadioButton;
    }

    public JCheckBox getRewriteExistingClassesCheckBox() {
        return rewriteExistingClassesCheckBox;
    }

    public JCheckBox getUseStringCheckBox() {
        return useStringCheckBox;
    }

    private void createUIComponents() {
        textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        textArea.setCodeFoldingEnabled(true);
        scrollView = new JScrollPane(textArea);
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(textArea);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public ButtonGroup getLanguageGroup() {

        return languageGroup;
    }
}
