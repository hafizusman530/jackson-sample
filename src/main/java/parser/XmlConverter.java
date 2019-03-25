
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
 
class XMLConverter extends JSONConverter {
 
    public XMLConverter() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        objectMapper = new XmlMapper(module);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
 
    public XMLConverter(boolean ignoreJAXBAnnotations){
        objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (ignoreJAXBAnnotations) {
            objectMapper.configure(com.fasterxml.jackson.databind.MapperFeature.USE_ANNOTATIONS, false);
        }
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        objectMapper.registerModule(module);
    }
 
    @Override
    public XMLConverter setNotNulls(boolean notNulls) {
        objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.notNulls = notNulls;
        if(notNulls) {
          objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return this;
    }
 
    @Override
    public <T> T deserialize(Class<T> type, Object value) throws Exception {
        // if we want to convert to a String or byte[] then use write operation
        T out = ConverterHelper.getT(type, value, objectMapper);
        if (out != null) return out;
 
        return null;
    }
 
    @Override
    public String serialize(Object value) throws JsonProcessingException {
        return serialize(value, new String[]{});
    }
 
    @Override
    public String serialize(Object value, String[] ignorableFieldNames) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().
                addFilter(FILTER, SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        return objectMapper.writer(filters).writeValueAsString(value);
 
    }
}
