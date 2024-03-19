package TestCases.UserStory;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class UserStory {

    private String cookie;
    private String issueID;

    @Test(priority = 0)
    public void loginJira() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/login.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().log().all().extract().response();

        System.out.println("------------------------------------------");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        JSONObject js = new JSONObject(response.asString());
         cookie = "JSESSIONID="+js.getJSONObject("session").get("value").toString();
        System.out.println(cookie);
    }

    @Test(priority = 1)
    public void createUserStory() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/userStory.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .header("Cookie", cookie)
                .contentType(ContentType.JSON).when().post("/rest/api/2/issue")
                .then().log().all().extract().response();

        JSONObject js = new JSONObject(response.asString());
       issueID = js.get("key").toString();

    }
    @Test(priority = 2)
    public void getUserStory(){

        RestAssured.given().baseUri("http://localhost:9009").header("Cookie",cookie)
                .contentType(ContentType.JSON).when().get("/rest/api/2/issue/"+issueID)
                .then().log().all().extract().response();
    }
}
