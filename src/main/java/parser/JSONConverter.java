package parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.util.List;

public class JSONConverter {
    private ObjectMapper objectMapper;
    private boolean notNulls;

    public JSONConverter() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        objectMapper.registerModule(module);
    }

    public JSONConverter(boolean ignoreJsonPropertyAnnotations) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (ignoreJsonPropertyAnnotations) {
            objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);

        }
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        objectMapper.registerModule(module);
    }
    public JSONConverter setNotNulls(boolean notNulls) {
        this.notNulls = notNulls;
        setSerializationInclusion(notNulls);
        return this;
    }
    private void setSerializationInclusion(boolean value) {
        if (value) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }
    public JSONConverter(List<JsonSerializer> jsonSerializers) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module;
        module = new SimpleModule();
        jsonSerializers.forEach(module::addSerializer);
        objectMapper.registerModule(module);
    }

    public <T> T deserialize(Class<T> type, Object value) throws Exception {
        T out = ConverterHelper.getT(type, value, objectMapper);
        if (out != null)
            return out;
        return null;
    }

    public String serialize(Object value) throws JsonProcessingException {
        return serialize(value, new String[]{});
    }

    public String serialize(Object value, String[] ignorableFieldNames) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().
                addFilter("filter", SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        return objectMapper.writer(filters).writeValueAsString(value);
    }
}
