package TestCases.UserStory;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class UserStory {

    //Jira login - jsessionid

    public static void main(String[] args) throws IOException, ParseException {
        FileReader fr = new FileReader("src/main/java/Files/login.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON)
                .when().post("/rest/auth/1/session").then().extract().response();


        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

    }
}
