package io.cana.intellij.pojogenerator.generator.common;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by vadim on 22.01.17.
 */
public class JsonItem {
    private String key;
    private String suffix;
    private JSONObject jsonObject;

    public JsonItem(JSONObject jsonObject, String key, String suffix) {
        this.key = key;
        this.suffix = suffix;
        this.jsonObject = jsonObject;
    }

    public String getKey() {
        return key;
    }

    public String getSuffix() {
        return suffix;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
