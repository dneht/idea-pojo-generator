package io.cana.intellij.pojogenerator.listeners;

import io.cana.intellij.pojogenerator.models.GenerationModel;

/**
 * Created by vadim on 24.09.16.
 */
public interface GuiFormEventListener {

    void onJsonDataObtained(GenerationModel model);
}
