package io.cana.intellij.pojogenerator.controllers;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import io.cana.intellij.pojogenerator.delegates.EnvironmentDelegate;
import io.cana.intellij.pojogenerator.delegates.GenerationDelegate;
import io.cana.intellij.pojogenerator.delegates.MessageDelegate;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.listeners.GuiFormEventListener;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import io.cana.intellij.pojogenerator.view.binders.GeneratorViewBinder;

import javax.inject.Inject;
import java.awt.*;

/**
 * Created by vadim on 24.09.16.
 */
public class GeneratePOJOActionController {
    @Inject
    EnvironmentDelegate environmentDelegate;
    @Inject
    MessageDelegate messageDelegate;
    @Inject
    GeneratorViewBinder generatorViewBinder;
    @Inject
    GenerationDelegate generationDelegate;

    @Inject
    public GeneratePOJOActionController() {
    }

    public void onActionHandled(AnActionEvent event) {
        try {
            proceed(event);
        } catch (RoboPluginException exception) {
            messageDelegate.onPluginExceptionHandled(exception);
        }
    }

    private void proceed(AnActionEvent event) throws RoboPluginException {
        final ProjectModel projectModel = environmentDelegate.obtainProjectModel(event);
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();

        generatorViewBinder.bindView(dialogBuilder, new GuiFormEventListener() {
            @Override
            public void onJsonDataObtained(GenerationModel generationModel) {
                window.dispose();
                generationDelegate.runGenerationTask(generationModel, projectModel);
            }
        });
    }
}
