package io.cana.intellij.pojogenerator.injections;

import dagger.Component;
import io.cana.intellij.pojogenerator.actions.GeneratePOJOAction;
import io.cana.intellij.pojogenerator.generator.postprocessing.common.AutoValueClassPostProcessor;
import io.cana.intellij.pojogenerator.generator.postprocessing.common.CommonJavaPostProcessor;
import io.cana.intellij.pojogenerator.generator.postprocessing.common.KotlinDataClassPostProcessor;
import io.cana.intellij.pojogenerator.listeners.GenerateActionListener;

import javax.inject.Singleton;

/**
 * Created by vadim on 28.09.16.
 */
@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(GeneratePOJOAction item);

    void inject(GenerateActionListener item);

    CommonJavaPostProcessor newCommonJavaPostProcessor();

    AutoValueClassPostProcessor newAutoValueClassPostProcessor();

    KotlinDataClassPostProcessor newKotlinDataClassPostProcessor();
}
