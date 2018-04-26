package io.cana.intellij.pojogenerator.generator;

import io.cana.intellij.pojogenerator.delegates.FileWriterDelegate;
import io.cana.intellij.pojogenerator.generator.common.ClassCreator;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import io.cana.intellij.pojogenerator.models.ProjectModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vadim on 22.10.16.
 */
public class ClassCreatorTest {
    @InjectMocks
    ClassCreator classCreator;
    @Mock
    PojoGenerator pojoGenerator;
    @Mock
    FileWriterDelegate fileWriterDelegate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateFiles() throws Exception {
        final ClassItem classItem = new ClassItem("");
        final Set<ClassItem> classItemSet = new HashSet<ClassItem>();
        classItemSet.add(classItem);
        final GenerationModel generationModel = new GenerationModel
                .Builder()
                .build();
        final ProjectModel projectModel = new ProjectModel
                .Builder()
                .build();
        when(pojoGenerator.generate(generationModel))
                .thenReturn(classItemSet);
        classCreator.generateFiles(generationModel, projectModel);
        verify(fileWriterDelegate)
                .writeFile(classItem, generationModel, projectModel);
    }
}