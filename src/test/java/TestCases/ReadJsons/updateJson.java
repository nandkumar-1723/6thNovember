package TestCases.ReadJsons;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class updateJson {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/sample.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(requestBody);

        js.getJSONArray("groupA").getJSONObject(0).put("salary","50cr");
        js.getJSONArray("groupB").getJSONObject(1).put("team","MI");

// Assignment
//        1. update the virat's ipl team
//        2. update the rohit sharma age
//        3. update KL rahil salary

        System.out.println(js);

    }
}
