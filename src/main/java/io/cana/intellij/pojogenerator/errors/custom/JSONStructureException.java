package io.cana.intellij.pojogenerator.errors.custom;

import io.cana.intellij.pojogenerator.errors.RoboPluginException;

/**
 * Created by vadim on 24.09.16.
 */
public class JSONStructureException extends RoboPluginException {
    public JSONStructureException() {
        super("JSON exception:", "incorrect structure");
    }
}
