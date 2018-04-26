package io.cana.intellij.pojogenerator.generator.postprocessing;

import io.cana.intellij.pojogenerator.generator.consts.annotations.AnnotationEnum;
import io.cana.intellij.pojogenerator.injections.Injector;
import io.cana.intellij.pojogenerator.models.GenerationModel;

import javax.inject.Inject;

/**
 * Created by vadim on 23.10.16.
 */
public class PostProcessorFactory {
    @Inject
    public PostProcessorFactory() {
    }

    public BasePostProcessor createPostProcessor(GenerationModel generationModel) {
        if (generationModel.isUseKotlin()) {
            return Injector.getAppComponent().newKotlinDataClassPostProcessor();

        } else if (generationModel.getAnnotationEnum() == AnnotationEnum.AUTO_VALUE_GSON) {
            return Injector.getAppComponent().newAutoValueClassPostProcessor();

        } else {
            return Injector.getAppComponent().newCommonJavaPostProcessor();
        }
    }
}
