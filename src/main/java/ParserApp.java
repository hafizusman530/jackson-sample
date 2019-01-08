import model.OrganizationResponse;
import parser.JsonDeserializer;
import parser.JsonSerializer;

import java.io.File;

public class ParserApp {
    public static void main(String[] args) {
        File file = new File("D:\\Open Source\\jackson-sample\\src\\main\\resources\\organizationResponse.json");
        OrganizationResponse organizationResponse = JsonDeserializer.fromJSONFile
                (OrganizationResponse.class, file);
        System.out.println(organizationResponse);

        String json = JsonSerializer.toJSON(organizationResponse);
        System.out.println(json);
    }
}
