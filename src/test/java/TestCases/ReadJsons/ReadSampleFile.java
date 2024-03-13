package TestCases.ReadJsons;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadSampleFile {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/sample.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();


        JSONObject js = new JSONObject(requestBody);

        // to get the virat's ipl team
        String team = js.getJSONArray("groupA").getJSONObject(0).get("team").toString();
        System.out.println(team);

        // to get the csk's first name
        String firstName = js.getJSONArray("groupB").getJSONObject(1).get("firstName").toString();
        System.out.println(firstName);

        //Assignment:
        //1. Captain of MI
        //2. Age of KL
        //3. Salary of virat
        //4. last name of MSD
    }
}
