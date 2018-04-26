package io.cana.intellij.pojogenerator.listeners;

import io.cana.intellij.pojogenerator.delegates.MessageDelegate;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.generator.consts.annotations.AnnotationEnum;
import io.cana.intellij.pojogenerator.generator.utils.ClassGenerateHelper;
import io.cana.intellij.pojogenerator.injections.Injector;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.view.ui.GeneratorVew;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Created by vadim on 24.09.16.
 */
public class GenerateActionListener implements ActionListener {
    @Inject
    MessageDelegate messageDelegate;
    @Inject
    ClassGenerateHelper classGenerateHelper;
    private GuiFormEventListener eventListener;
    private GeneratorVew generatorVew;

    public GenerateActionListener(GeneratorVew generatorVew,
                                  GuiFormEventListener eventListener) {
        this.generatorVew = generatorVew;
        this.eventListener = eventListener;
        Injector.getAppComponent().inject(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JTextArea textArea = generatorVew.getTextArea();
        final JTextField textField = generatorVew.getClassNameTextField();
        final JTextField suffixTextField = generatorVew.getObjectSuffixTextField();

        final AnnotationEnum annotationEnum = resolveAnnotationItem();

        final boolean useKotlin = generatorVew.getKotlinCheckBox().isSelected();
        final boolean rewriteClasses = generatorVew.getRewriteExistingClassesCheckBox().isSelected();
        final boolean useSetters = generatorVew.getUseSettersCheckBox().isSelected();
        final boolean useGetters = generatorVew.getUseGettersCheckBox().isSelected();
        final boolean useStrings = generatorVew.getUseStringCheckBox().isSelected();

        String content = textArea.getText();
        final String className = textField.getText();
        final String objectSuffix = suffixTextField.getText();
        try {
            classGenerateHelper.validateClassName(className);
            content = classGenerateHelper.validateJsonContent(content);
            eventListener.onJsonDataObtained(new GenerationModel
                    .Builder()
                    .useKotlin(useKotlin)
                    .setAnnotationItem(annotationEnum)
                    .setContent(content)
                    .setSettersAvailable(useSetters)
                    .setGettersAvailable(useGetters)
                    .setToStringAvailable(useStrings)
                    .setRootClassName(className)
                    .setObjectSuffix(objectSuffix)
                    .setRewriteClasses(rewriteClasses)
                    .build());

        } catch (RoboPluginException exception) {
            messageDelegate.onPluginExceptionHandled(exception);
        }
    }

    private AnnotationEnum resolveAnnotationItem() {
        final ButtonGroup buttonGroup = generatorVew.getTypeButtonGroup();
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons
                .hasMoreElements(); ) {
            final AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                for (AnnotationEnum annotationEnum : AnnotationEnum.values()) {
                    if (annotationEnum.getText().equals(button.getText())) {
                        return annotationEnum;
                    }
                }
            }
        }
        return AnnotationEnum.NONE;
    }
}
