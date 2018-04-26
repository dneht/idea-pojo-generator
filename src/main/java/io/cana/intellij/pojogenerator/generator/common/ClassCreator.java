package io.cana.intellij.pojogenerator.generator.common;

import io.cana.intellij.pojogenerator.delegates.FileWriterDelegate;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.generator.PojoGenerator;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by vadim on 02.10.16.
 */
public class ClassCreator {
    @Inject
    PojoGenerator pojoGenerator;
    @Inject
    FileWriterDelegate fileWriterDelegate;

    @Inject
    public ClassCreator() {
    }

    public void generateFiles(@NotNull GenerationModel generationModel,
                              @NotNull ProjectModel projectModel) throws RoboPluginException {
        final Set<ClassItem> classItemSet = pojoGenerator.generate(generationModel);
        for (ClassItem classItem : classItemSet) {
            fileWriterDelegate.writeFile(classItem, generationModel, projectModel);
        }
    }
}
