package io.cana.intellij.pojogenerator.generator;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.common.JsonItem;
import io.cana.intellij.pojogenerator.generator.processing.ClassProcessor;
import io.cana.intellij.pojogenerator.models.GenerationModel;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by vadim on 22.09.16.
 */
public class PojoGenerator {
    @Inject
    ClassProcessor processor;

    @Inject
    public PojoGenerator() {
    }

    public Set<ClassItem> generate(GenerationModel model) {
        final JSONObject jsonObject = JSONObject.parseObject(model.getContent(), Feature.OrderedField);
        final Map<String, ClassItem> map = new HashMap<String, ClassItem>();
        final JsonItem jsonItem = new JsonItem(jsonObject, model.getRootClassName(), model.getObjectSuffix());
        processor.proceed(jsonItem, map);
        return new HashSet<ClassItem>(map.values());
    }
}
