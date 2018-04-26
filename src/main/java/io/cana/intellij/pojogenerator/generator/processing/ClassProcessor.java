package io.cana.intellij.pojogenerator.generator.processing;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.cana.intellij.pojogenerator.generator.common.ClassField;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.common.JsonItem;
import io.cana.intellij.pojogenerator.generator.common.JsonItemArray;
import io.cana.intellij.pojogenerator.generator.consts.ClassEnum;
import io.cana.intellij.pojogenerator.generator.consts.templates.ImportsTemplate;
import io.cana.intellij.pojogenerator.generator.utils.ClassGenerateHelper;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by vadim on 23.09.16.
 */
public class ClassProcessor {
    @Inject
    ClassGenerateHelper classGenerateHelper;

    @Inject
    public ClassProcessor() {
    }

    public void proceed(JsonItem jsonItem, final Map<String, ClassItem> itemMap) {
        final String objectSuffix = jsonItem.getSuffix();
        final String itemName = classGenerateHelper.getClassNameWithItemPostfix(jsonItem.getKey(), objectSuffix, false);
        final ClassItem classItem = new ClassItem(itemName);
        classItem.addClassImport(ImportsTemplate.SERIALIZABLE);

        final JSONObject jsonObject = jsonItem.getJsonObject();
        for (final String jsonObjectKey : jsonObject.keySet()) {
            final Object object = jsonObject.get(jsonObjectKey);
            final InnerObjectResolver innerObjectResolver = new InnerObjectResolver() {

                @Override
                public void onInnerObjectIdentified(ClassEnum classType) {
                    classItem.addClassField(jsonObjectKey, new ClassField(classType));
                }

                @Override
                public void onJsonObjectIdentified() {
                    final String className = classGenerateHelper.getClassNameWithItemPostfix(jsonObjectKey, objectSuffix, false);
                    final ClassField classField = new ClassField(className);
                    final JsonItem jsonItem = new JsonItem((JSONObject) object, jsonObjectKey, objectSuffix);

                    classItem.addClassField(jsonObjectKey, classField);
                    proceed(jsonItem, itemMap);
                }

                @Override
                public void onJsonArrayIdentified() {
                    final JSONArray jsonArray = (JSONArray) object;
                    classItem.addClassImport(ImportsTemplate.LIST);

                    final ClassField classField = new ClassField();
                    if (jsonArray.size() == 0) {
                        classField.setClassField(new ClassField(ClassEnum.OBJECT));
                        classItem.addClassField(jsonObjectKey, classField);

                    } else {
                        final JsonItemArray jsonItemArray = new JsonItemArray(jsonArray, jsonObjectKey);
                        proceedArray(jsonItemArray, objectSuffix, classField, itemMap);
                        classItem.addClassField(jsonObjectKey, classField);
                    }
                }
            };
            innerObjectResolver.resolveClassType(object);
        }
        appendItemsMap(itemMap, classItem);
    }

    private void appendItemsMap(Map<String, ClassItem> itemMap, ClassItem classItem) {
        final String className = classItem.getClassName();
        if (itemMap.containsKey(className)) {
            final ClassItem storedClassItem = itemMap.get(className);
            if (storedClassItem.getClassFields().size() > classItem.getClassFields().size()) {
                return;
            }
        }
        itemMap.put(className, classItem);
    }

    private void proceedArray(final JsonItemArray jsonItemArray,
                              final String objectSuffix,
                              final ClassField classField,
                              final Map<String, ClassItem> itemMap) {
        final String itemName = classGenerateHelper.getClassNameWithItemPostfix(jsonItemArray.getKey(), objectSuffix, true);
        if (jsonItemArray.getJsonArray().size() != 0) {
            final Object object = jsonItemArray.getJsonArray().get(0);
            final InnerObjectResolver innerObjectResolver = new InnerObjectResolver() {

                @Override
                public void onInnerObjectIdentified(ClassEnum classType) {
                    classField.setClassField(new ClassField(classType));
                }

                @Override
                public void onJsonObjectIdentified() {
                    classField.setClassField(new ClassField(itemName));
                    final JsonItem jsonItem = new JsonItem((JSONObject) object, itemName, objectSuffix);
                    proceed(jsonItem, itemMap);
                }

                @Override
                public void onJsonArrayIdentified() {
                    classField.setClassField(new ClassField());
                    final JsonItemArray jsonItemArray = new JsonItemArray((JSONArray) object, itemName);
                    proceedArray(jsonItemArray, objectSuffix, classField, itemMap);
                }
            };
            innerObjectResolver.resolveClassType(object);

        } else {
            classField.setClassField(new ClassField(ClassEnum.OBJECT));
        }
    }
}
