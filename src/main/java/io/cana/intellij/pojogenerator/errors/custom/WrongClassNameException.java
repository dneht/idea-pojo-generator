package io.cana.intellij.pojogenerator.errors.custom;

import io.cana.intellij.pojogenerator.errors.RoboPluginException;

/**
 * Created by vadim on 25.09.16.
 */
public class WrongClassNameException extends RoboPluginException {
    public WrongClassNameException() {
        super("Wrong class name:", "you should set root class name");
    }
}
