package io.cana.intellij.pojogenerator.generator.postprocessing.common;

import io.cana.intellij.pojogenerator.generator.common.ClassField;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.consts.annotations.AnnotationEnum;
import io.cana.intellij.pojogenerator.generator.consts.annotations.KotlinAnnotations;
import io.cana.intellij.pojogenerator.generator.consts.templates.ClassTemplate;
import io.cana.intellij.pojogenerator.generator.consts.templates.ImportsTemplate;
import io.cana.intellij.pojogenerator.generator.postprocessing.BasePostProcessor;
import io.cana.intellij.pojogenerator.models.FieldModel;
import io.cana.intellij.pojogenerator.models.GenerationModel;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;

/**
 * This is the KotlinDataClassPostProcessor class
 * Please put some info here.
 *
 * @author Wafer Li
 * @since 16/10/24 00:30
 */
public class KotlinDataClassPostProcessor extends BasePostProcessor {
    @Inject
    public KotlinDataClassPostProcessor() {
    }

    @Override
    protected StringBuilder proceedClassImports(ClassItem classItem) {
        final Set<String> imports = classItem.getClassImports();
        imports.remove(ImportsTemplate.LIST);
        final StringBuilder importsBuilder = new StringBuilder();
        for (String importItem : imports) {
            importsBuilder.append(importItem.replace(";", ""));
            importsBuilder.append(ClassTemplate.NEW_LINE);
        }
        return importsBuilder;
    }

    @Override
    public String proceedClassBody(ClassItem classItem, GenerationModel generationModel) {
        final StringBuilder classBodyBuilder = new StringBuilder();
        final Map<String, ClassField> classFields = classItem.getClassFields();
        for (String objectName : classFields.keySet()) {
            classBodyBuilder.append(classTemplateHelper.createKotlinDataClassField(
                    new FieldModel.Builder()
                            .setFieldName(objectName)
                            .setClassType(classFields.get(objectName).getKotlinItem())
                            .setAnnotation(classItem.getAnnotation())
                            .setFieldNameFormatted(generateHelper.formatClassField(objectName))
                            .build()
            ));
        }
        generateHelper.updateClassModel(classBodyBuilder);
        return classBodyBuilder.toString();
    }

    @Override
    protected String createClassItemText(String packagePath, String imports, String classTemplate) {
        return classTemplateHelper
                .createClassItemWithoutSemicolon(packagePath,
                        imports,
                        classTemplate);
    }

    @Override
    protected void applyAnnotations(AnnotationEnum item, ClassItem classItem) {
        switch (item) {
            case GSON: {
                generateHelper.setAnnotations(classItem,
                        KotlinAnnotations.GSON.CLASS_ANNOTATION,
                        KotlinAnnotations.GSON.ANNOTATION,
                        ImportsTemplate.GSON.IMPORTS);
                break;
            }
            case LOGAN_SQUARE: {
                generateHelper.setAnnotations(classItem,
                        KotlinAnnotations.LOGAN_SQUARE.CLASS_ANNOTATION,
                        KotlinAnnotations.LOGAN_SQUARE.ANNOTATION,
                        ImportsTemplate.LOGAN_SQUARE.IMPORTS);
                break;
            }
            case JACKSON: {
                generateHelper.setAnnotations(classItem,
                        KotlinAnnotations.JACKSON.CLASS_ANNOTATION,
                        KotlinAnnotations.JACKSON.ANNOTATION,
                        ImportsTemplate.JACKSON.IMPORTS);
                break;
            }
            case FAST_JSON: {
                generateHelper.setAnnotations(classItem,
                        KotlinAnnotations.FAST_JSON.CLASS_ANNOTATION,
                        KotlinAnnotations.FAST_JSON.ANNOTATION,
                        ImportsTemplate.FAST_JSON.IMPORTS);
                break;
            }
            case MOSHI: {
                generateHelper.setAnnotations(classItem,
                        KotlinAnnotations.MOSHI.CLASS_ANNOTATION,
                        KotlinAnnotations.MOSHI.ANNOTATION,
                        ImportsTemplate.MOSHI.IMPORTS);
                break;
            }
        }
    }

    @Override
    public String createClassTemplate(ClassItem classItem, String classBody) {
        return classTemplateHelper.createClassBodyKotlinDataClass(classItem, classBody);
    }
}
