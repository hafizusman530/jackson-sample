package parser;

import java.util.ArrayList;
import java.util.List;

public class JsonSerializer {
    public static String toJSON(Object object) {
        return toJSON(object, true, new String[]{}, new ArrayList<>());
    }

    public static String toJSON(Object object, boolean skipNull) {
        return toJSON(object, skipNull, new String[]{}, new ArrayList<>());
    }

    public static String toJSON(Object object, boolean skipNull, String[] ignoreFields) {
        return toJSON(object, skipNull, ignoreFields, new ArrayList<>());
    }

    public static String toJSON(Object object, boolean skipNull, List<com.fasterxml.jackson.databind.JsonSerializer> jsonSerializers) {
        return toJSON(object, skipNull, new String[]{}, jsonSerializers);
    }

    public static String toJSON(Object object, boolean skipNull, boolean ignoreAnnotations) {
        return toJSON(object, skipNull, new String[]{}, ignoreAnnotations);
    }

    public static String toJSON(Object object, boolean skipNull, String[] ignoreFields, boolean ignoreAnnotations) {
        try {
            JSONConverter jsonConverter = new JSONConverter(ignoreAnnotations).setNotNulls(skipNull);
            return jsonConverter.serialize(object, ignoreFields);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSON(Object object, boolean skipNull, String[] ignoreFields, List<com.fasterxml.jackson.databind.JsonSerializer> jsonSerializers) {
        try {
            JSONConverter jsonConverter = new JSONConverter(jsonSerializers).setNotNulls(skipNull);
            return jsonConverter.serialize(object, ignoreFields);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
