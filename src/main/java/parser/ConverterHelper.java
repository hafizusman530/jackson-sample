package parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

class ConverterHelper {
    static <T> T getT(Class<T> type, Object value, ObjectMapper mapper) throws java.io.IOException {
        if (String.class.isAssignableFrom(type)) {
            String out = mapper.writeValueAsString(value);
            return type.cast(out);
        } else if (byte[].class.isAssignableFrom(type)) {
            byte[] out = mapper.writeValueAsBytes(value);
            return type.cast(out);
        } else if (mapper.canSerialize(type)) {
            if (String.class.isAssignableFrom(value.getClass())) {
                return mapper.readValue((String) value, type);
            } else if (byte[].class.isAssignableFrom(value.getClass())) {
                return mapper.readValue((byte[]) value, type);
            } else if (File.class.isAssignableFrom(value.getClass())) {
                return mapper.readValue((File) value, type);
            } else if (InputStream.class.isAssignableFrom(value.getClass())) {
                return mapper.readValue((InputStream) value, type);
            } else if (Reader.class.isAssignableFrom(value.getClass())) {
                return mapper.readValue((Reader) value, type);
            } else {
                return mapper.convertValue(value, type);
            }
        }
        return null;
    }
}
