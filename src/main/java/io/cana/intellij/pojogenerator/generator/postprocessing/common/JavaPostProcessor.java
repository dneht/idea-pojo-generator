package io.cana.intellij.pojogenerator.generator.postprocessing.common;

import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.consts.annotations.AnnotationEnum;
import io.cana.intellij.pojogenerator.generator.consts.annotations.PojoAnnotations;
import io.cana.intellij.pojogenerator.generator.consts.templates.ImportsTemplate;
import io.cana.intellij.pojogenerator.generator.postprocessing.BasePostProcessor;

/**
 * Created by vadim on 24.10.16.
 */
public abstract class JavaPostProcessor extends BasePostProcessor {

    protected void applyAnnotations(AnnotationEnum item, ClassItem classItem) {
        switch (item) {
            case GSON: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.GSON.CLASS_ANNOTATION,
                        PojoAnnotations.GSON.ANNOTATION,
                        ImportsTemplate.GSON.IMPORTS);
                break;
            }
            case LOGAN_SQUARE: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.LOGAN_SQUARE.CLASS_ANNOTATION,
                        PojoAnnotations.LOGAN_SQUARE.ANNOTATION,
                        ImportsTemplate.LOGAN_SQUARE.IMPORTS);
                break;
            }
            case JACKSON: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.JACKSON.CLASS_ANNOTATION,
                        PojoAnnotations.JACKSON.ANNOTATION,
                        ImportsTemplate.JACKSON.IMPORTS);
                break;
            }
            case FAST_JSON: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.FAST_JSON.CLASS_ANNOTATION,
                        PojoAnnotations.FAST_JSON.ANNOTATION,
                        ImportsTemplate.FAST_JSON.IMPORTS);
                break;
            }
            case AUTO_VALUE_GSON: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.AUTO_VALUE_GSON.CLASS_ANNOTATION,
                        PojoAnnotations.AUTO_VALUE_GSON.ANNOTATION,
                        ImportsTemplate.AUTO_VALUE_GSON.IMPORTS);
                break;
            }
            case MOSHI: {
                generateHelper.setAnnotations(classItem,
                        PojoAnnotations.MOSHI.CLASS_ANNOTATION,
                        PojoAnnotations.MOSHI.ANNOTATION,
                        ImportsTemplate.MOSHI.IMPORTS);
                break;
            }
        }
    }
}