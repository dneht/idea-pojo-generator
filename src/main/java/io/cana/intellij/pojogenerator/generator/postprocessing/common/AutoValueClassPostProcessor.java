package io.cana.intellij.pojogenerator.generator.postprocessing.common;

import io.cana.intellij.pojogenerator.generator.common.ClassField;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.consts.templates.ClassTemplate;
import io.cana.intellij.pojogenerator.models.FieldModel;
import io.cana.intellij.pojogenerator.models.GenerationModel;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by vadim on 23.10.16.
 */
public class AutoValueClassPostProcessor extends JavaPostProcessor {
    @Inject
    public AutoValueClassPostProcessor() {
    }

    @Override
    public String proceedClassBody(ClassItem classItem, GenerationModel generationModel) {
        final StringBuilder classBodyBuilder = new StringBuilder();
        final Map<String, ClassField> classFields = classItem.getClassFields();
        for (String objectName : classFields.keySet()) {
            classBodyBuilder.append(classTemplateHelper.createAutoValueField(
                    new FieldModel.Builder()
                            .setClassType(classFields.get(objectName).getJavaItem())
                            .setAnnotation(classItem.getAnnotation())
                            .setFieldName(objectName)
                            .setFieldNameFormatted(generateHelper.formatClassField(objectName))
                            .build())
            );
        }
        classBodyBuilder.append(ClassTemplate.NEW_LINE);
        classBodyBuilder.append(classTemplateHelper.createTypeAdapter(classItem));
        return classBodyBuilder.toString();
    }

    @Override
    public String createClassTemplate(ClassItem classItem, String classBody) {
        return classTemplateHelper.createClassBodyAbstract(classItem, classBody);
    }
}
