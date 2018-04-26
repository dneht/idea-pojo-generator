package io.cana.intellij.pojogenerator.controllers;

import com.intellij.openapi.actionSystem.AnActionEvent;
import io.cana.intellij.pojogenerator.delegates.EnvironmentDelegate;
import io.cana.intellij.pojogenerator.delegates.GenerationDelegate;
import io.cana.intellij.pojogenerator.delegates.MessageDelegate;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import io.cana.intellij.pojogenerator.view.binders.GeneratorViewBinder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vadim on 22.10.16.
 */
public class GeneratePOJOActionControllerTest {
    @InjectMocks
    GeneratePOJOActionController generatePOJOActionController;

    @Mock
    EnvironmentDelegate environmentDelegate;
    @Mock
    MessageDelegate messageDelegate;
    @Mock
    GeneratorViewBinder generatorViewBinder;
    @Mock
    GenerationDelegate generationDelegate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onActionHandled() throws Exception {
        final ProjectModel projectModel = new ProjectModel
                .Builder()
                .build();
        AnActionEvent event = Mockito.mock(AnActionEvent.class);

        when(environmentDelegate.obtainProjectModel(event))
                .thenReturn(projectModel);
        generatePOJOActionController.onActionHandled(event);
        verify(generatorViewBinder).bindView(any(), any());
    }

    @Test
    public void onActionHandled_withError() throws Exception {
        final RoboPluginException exception = new RoboPluginException("", "");
        AnActionEvent event = Mockito.mock(AnActionEvent.class);

        when(environmentDelegate.obtainProjectModel(event))
                .thenThrow(exception);
        generatePOJOActionController.onActionHandled(event);
        verify(messageDelegate).onPluginExceptionHandled(exception);
    }
}