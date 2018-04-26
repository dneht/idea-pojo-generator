package io.cana.intellij.pojogenerator.generator.common;


import com.alibaba.fastjson.JSONArray;

/**
 * Created by vadim on 22.01.17.
 */
public class JsonItemArray {
    private String key;
    private JSONArray jsonArray;

    public JsonItemArray(JSONArray jsonArray, String key) {
        this.key = key;
        this.jsonArray = jsonArray;
    }

    public String getKey() {
        return key;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }
}
