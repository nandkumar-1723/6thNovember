package TestCases.ReadJsons;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class practice {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/practice.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(requestBody);

        String id = js.getJSONObject("glossary").getJSONObject("GlossDiv").getJSONObject("GlossList").getJSONObject("GlossEntry")
                .get("ID").toString();

        System.out.println(id);
    }
}
