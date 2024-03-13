package TestCases.ReadJsons;

import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadJsonFile {


     static public void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/requestbody.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);

        System.out.println(2+"3");


    }
}
