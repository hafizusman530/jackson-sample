package parser;

import java.io.File;

public class JsonDeserializer {

    public static <T> T fromJSONFile(Class<T> clazz, File file) {
        try {
            return new JSONConverter().deserialize(clazz, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
