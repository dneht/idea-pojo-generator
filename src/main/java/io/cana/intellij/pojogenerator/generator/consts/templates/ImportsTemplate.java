package io.cana.intellij.pojogenerator.generator.consts.templates;

/**
 * Created by vadim on 02.10.16.
 */
public interface ImportsTemplate {
    String SERIALIZABLE = "import java.io.Serializable;";

    String LIST = "import java.util.List;";

    String GENERATED = "import javax.annotation.Generated;";

    String SERIALIZED_NAME = "import com.google.gson.annotations.SerializedName;";

    String JSON_OBJECT = "import com.bluelinelabs.logansquare.annotation.JsonObject;";
    String JSON_FIELD = "import com.bluelinelabs.logansquare.annotation.JsonField;";

    String JSON_PROPERTY = "import com.fasterxml.jackson.annotation.JsonProperty;";

    String FAST_JSON_PROPERTY = "import com.alibaba.fastjson.annotation.JSONField;";

    String MOSHI_PROPERTY = "import com.squareup.moshi.Json;";

    String AUTO_VALUE = "import com.google.auto.value.AutoValue;";
    String TYPED_ADAPTER = "import com.google.gson.TypeAdapter;";
    String GSON_IMPORT = "import com.google.gson.Gson;";

    interface GSON {
        String[] IMPORTS = {GENERATED, SERIALIZED_NAME};
    }

    interface LOGAN_SQUARE {
        String[] IMPORTS = {GENERATED, SERIALIZED_NAME, JSON_OBJECT, JSON_FIELD};
    }

    interface JACKSON {
        String[] IMPORTS = {GENERATED, JSON_PROPERTY};
    }

    interface AUTO_VALUE_GSON {
        String[] IMPORTS = {GENERATED, SERIALIZED_NAME, AUTO_VALUE, TYPED_ADAPTER, GSON_IMPORT};
    }

    interface FAST_JSON {
        String[] IMPORTS = {GENERATED, FAST_JSON_PROPERTY};
    }

    interface MOSHI {
        String[] IMPORTS = {GENERATED, MOSHI_PROPERTY};
    }
}
