package io.cana.intellij.pojogenerator.generator.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.base.CaseFormat;
import io.cana.intellij.pojogenerator.errors.RoboPluginException;
import io.cana.intellij.pojogenerator.errors.custom.JSONStructureException;
import io.cana.intellij.pojogenerator.errors.custom.WrongClassNameException;
import io.cana.intellij.pojogenerator.generator.common.ClassItem;
import io.cana.intellij.pojogenerator.generator.consts.reserved.ReservedWords;
import io.cana.intellij.pojogenerator.generator.consts.templates.ArrayItemsTemplate;
import io.cana.intellij.pojogenerator.generator.consts.templates.ClassTemplate;

import javax.inject.Inject;

/**
 * Created by vadim on 25.09.16.
 */
public class ClassGenerateHelper {
    @Inject
    public ClassGenerateHelper() {
    }

    public String validateJsonContent(String content) throws RoboPluginException {
        try {
            JSONObject.parseObject(content, Feature.OrderedField);
        } catch (Exception exception) {
            try {
                final JSONArray jsonArray = JSONArray.parseArray(content);
                if (jsonArray.size() > 0) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.keySet().size() == 0) {
                        throw new JSONStructureException();
                    }
                    return jsonObject.toString();
                } else {
                    throw new JSONStructureException();
                }
            } catch (Exception arrayException) {
                throw new JSONStructureException();
            }
        }
        return content;
    }

    public void validateClassName(String name) throws RoboPluginException {
        if (null != name && name.length() > 1) {
            final String pattern = "^[a-zA-Z0-9]*$";
            if (name.matches(pattern)) {
                return;
            }
        }
        throw new WrongClassNameException();
    }

    public String updateClassBody(String classBody) {
        if (null != classBody && classBody.length() > 0) {
            final int lastIndex = classBody.length() - 1;
            if (classBody.charAt(lastIndex) == '\n') {
                return classBody.substring(0, lastIndex);
            }
        }
        return classBody;
    }

    public String formatClassName(String name) {
        return upperCaseFirst(proceedField(name));
    }

    public String getClassNameWithItemPostfix(String name, String input, boolean flag) {
        if (null != input && !input.isEmpty()) {
            if (name.endsWith(input)) {
                return formatClassName(name);
            } else {
                return String.format("%s" + input, upperCaseFirst(proceedField(name)));
            }
        } else {
            if (flag) {
                return String.format(ArrayItemsTemplate.ITEM_NAME, upperCaseFirst(proceedField(name)));
            } else {
                return formatClassName(name);
            }
        }
    }

    public String upperCaseFirst(String name) {
        if (name.length() > 1) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        return name;
    }

    public String formatClassField(String name) {
        return lowerCaseFirst(proceedField(name));
    }

    public String lowerCaseFirst(String name) {
        if (name.length() > 1) {
            name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        }
        return name;
    }

    public void setAnnotations(ClassItem classItem, String classAnnotation,
                               String annotation, String[] imports) {
        classItem.setClassAnnotation(classAnnotation);
        classItem.setAnnotation(annotation);

        for (String value : imports) {
            classItem.addClassImport(value);
        }
    }

    public void updateClassModel(StringBuilder classBodyBuilder) {
        if (classBodyBuilder.length() == 0) {
            classBodyBuilder.append(ClassTemplate.FIELD_KOTLIN_DOT_DEFAULT);
        } else {
            classBodyBuilder.deleteCharAt(classBodyBuilder.lastIndexOf(","));
        }
    }

    public String proceedField(String objectName) {
        objectName = objectName
                .replaceAll("[^A-Za-z0-9]", "_")
                .replaceAll("_{2,}", "_");

        final boolean isDigitFirst = (objectName.length() > 0 && Character.isDigit(objectName.charAt(0)))
                || (objectName.length() > 1 && (objectName.charAt(0) == '_' &&
                Character.isDigit(objectName.charAt(1))));

        if (objectName.length() == 0 || isDigitFirst || ReservedWords.WORDS_SET.contains(objectName)) {
            objectName = "json_member_" + objectName;
        }
        objectName = objectName.replaceAll("([A-Z])", "_$1");
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, objectName);
    }
}
