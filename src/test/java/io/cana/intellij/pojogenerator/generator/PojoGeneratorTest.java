package io.cana.intellij.pojogenerator.generator;

import com.alibaba.fastjson.JSONObject;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.processing.ClassProcessor;
import io.cana.intellij.pojogenerator.models.GenerationModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by vadim on 22.10.16.
 */
public class PojoGeneratorTest {
    @InjectMocks
    PojoGenerator pojoGenerator;
    @Mock
    ClassProcessor classProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generate() throws Exception {
        final GenerationModel generationModel = new GenerationModel
                .Builder()
                .setContent(new JSONObject().toString())
                .build();
        Set<ClassItem> classItemSet = pojoGenerator.generate(generationModel);
        assertNotNull(classItemSet);
    }
}