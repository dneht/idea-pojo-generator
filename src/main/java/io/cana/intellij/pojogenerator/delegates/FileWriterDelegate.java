package io.cana.intellij.pojogenerator.delegates;

import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.errors.custom.FileWriteException;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.postprocessing.BasePostProcessor;
import io.cana.intellij.pojogenerator.generator.postprocessing.PostProcessorFactory;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import org.apache.commons.io.FileUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

/**
 * Created by vadim on 25.09.16.
 */
public class FileWriterDelegate {
    @Inject
    MessageDelegate messageDelegate;
    @Inject
    PostProcessorFactory factory;

    @Inject
    public FileWriterDelegate() {
    }

    public void writeFile(ClassItem classItem, GenerationModel generationModel,
                          ProjectModel projectModel) throws RoboPluginException {
        final String path = projectModel.getDirectory().getVirtualFile().getPath();
        final String fileName = classItem.getClassName()
                + (generationModel.isUseKotlin() ? ".kt" : ".java");
        final File file = new File(path + File.separator + fileName);
        try {
            if (file.exists()) {
                if (generationModel.isRewriteClasses()) {
                    file.delete();
                    messageDelegate.logEventMessage("updated " + fileName);
                } else {
                    messageDelegate.logEventMessage("skipped " + fileName);
                }

            } else {
                messageDelegate.logEventMessage("created " + fileName);
            }

            if (!file.exists()) {
                file.createNewFile();
                writeToFile(classItem, generationModel, projectModel, file);
            }
        } catch (IOException e) {
            throw new FileWriteException(e.getMessage());
        }
    }

    private void writeToFile(ClassItem classItem, GenerationModel generationModel,
                             ProjectModel projectModel, File file) throws IOException {
        final String content = prepareClass(classItem, generationModel, projectModel);
        FileUtils.writeStringToFile(file, content);
    }

    private String prepareClass(ClassItem classItem, GenerationModel generationModel,
                                ProjectModel projectModel) {
        classItem.setPackagePath(projectModel.getPackageName());
        final BasePostProcessor postProcessor = factory.createPostProcessor(generationModel);
        return postProcessor.proceed(classItem, generationModel);
    }
}
