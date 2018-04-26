package io.cana.intellij.pojogenerator.delegates;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.generator.common.ClassCreator;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by vadim on 02.10.16.
 */
public class GenerationDelegate {
    @Inject
    ClassCreator classCreator;
    @Inject
    EnvironmentDelegate environmentDelegate;
    @Inject
    MessageDelegate messageDelegate;

    @Inject
    public GenerationDelegate() {
    }

    public void runGenerationTask(final GenerationModel generationModel,
                                  final ProjectModel projectModel) {
        ProgressManager.getInstance().run(new Task.Backgroundable(projectModel.getProject(),
                "RoboPOJO Generation", false) {

            public void run(@NotNull ProgressIndicator indicator) {
                try {
                    classCreator.generateFiles(generationModel, projectModel);
                    messageDelegate.showSuccessMessage();

                } catch (RoboPluginException e) {
                    messageDelegate.onPluginExceptionHandled(e);
                } finally {
                    indicator.stop();
                    environmentDelegate.refreshProject(projectModel);
                }
            }
        });
    }
}
