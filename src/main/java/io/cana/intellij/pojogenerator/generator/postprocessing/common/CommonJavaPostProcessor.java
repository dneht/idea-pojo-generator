package io.cana.intellij.pojogenerator.generator.postprocessing.common;

import io.cana.intellij.pojogenerator.generator.common.ClassField;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.consts.templates.ClassTemplate;
import io.cana.intellij.pojogenerator.models.FieldModel;
import io.cana.intellij.pojogenerator.models.GenerationModel;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by vadim on 25.09.16.
 */
public class CommonJavaPostProcessor extends JavaPostProcessor {
    @Inject
    public CommonJavaPostProcessor() {
    }

    @Override
    public String proceedClassBody(ClassItem classItem, GenerationModel generationModel) {
        final StringBuilder classBodyBuilder = new StringBuilder();
        final StringBuilder classMethodBuilder = new StringBuilder();
        final Map<String, ClassField> classFields = classItem.getClassFields();

        for (String objectName : classFields.keySet()) {
            final String classItemValue = classFields.get(objectName).getJavaItem();
            final String itemNameFormatted = generateHelper.formatClassField(objectName);
            classBodyBuilder.append(classTemplateHelper.createFiled(
                    new FieldModel
                            .Builder()
                            .setClassType(classItemValue)
                            .setFieldNameFormatted(itemNameFormatted)
                            .setFieldName(objectName)
                            .setAnnotation(classItem.getAnnotation())
                            .build()
            ));
            if (generationModel.isUseSetters()) {
                classMethodBuilder.append(ClassTemplate.NEW_LINE);
                classMethodBuilder.append(classTemplateHelper.createSetter(
                        itemNameFormatted,
                        classItemValue));

            }
            if (generationModel.isUseGetters()) {
                classMethodBuilder.append(ClassTemplate.NEW_LINE);
                classMethodBuilder.append(classTemplateHelper.createGetter(
                        itemNameFormatted,
                        classItemValue));
            }
        }
        if (generationModel.isUseStrings()) {
            classMethodBuilder.append(ClassTemplate.NEW_LINE);
            classMethodBuilder.append(classTemplateHelper.createToString(
                    classItem
            ));
        }
        classBodyBuilder.append(classMethodBuilder);
        return classBodyBuilder.toString();
    }

    @Override
    public String createClassTemplate(ClassItem classItem, String classBody) {
        return classTemplateHelper.createClassBody(classItem, classBody);
    }
}
