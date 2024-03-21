package TestCases.Issues;

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
public class Task {
    private String cookie;
    private String issueId;

    @Test(priority = 1)
    public void JiraLogin() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/login.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().log().all().extract().response();

        JSONObject js = new JSONObject(response.asString());
         cookie = "JSESSIONID="+js.getJSONObject("session").get("value").toString();
    }

    @Test(priority = 2)
    public void createTask() throws IOException, ParseException {
        FileReader fr = new FileReader("src/main/java/Files/Task.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON).header("Cookie", cookie)
                .when().post("/rest/api/2/issue").then().log().all().extract().response();

        JSONObject js = new JSONObject(response.asString());
        issueId = js.get("key").toString();
    }
    @Test(priority = 3)
    public void getTask(){
        RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON)
                .header("Cookie",cookie).when().get("/rest/api/2/issue/"+issueId)
                .then().log().all().extract().response();

    }

    @Test(priority = 4)
    public void updateTask() throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/Files/Task.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println("Origional Request body "+requestBody);

        //to update the task summary
        JSONObject js= new JSONObject(requestBody);
        js.getJSONObject("fields").put("summary","i wants to update the task");
        System.out.println("Actual request body "+js);

        RestAssured.given().baseUri("http://localhost:9009").body(js.toString())
                .header("Cookie",cookie).contentType(ContentType.JSON)
                .when().put("/rest/api/2/issue/"+issueId).then().log().all().extract().response();
    }

    @Test(priority = 5)
    public void getUpdatedTask(){
        RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON)
                .header("Cookie",cookie).when().get("/rest/api/2/issue/"+issueId)
                .then().log().all().extract().response();

    }

    @Test(priority = 6)
    public void deleteTask(){
        RestAssured.given().baseUri("http://localhost:9009").header("Cookie",cookie)
                .contentType(ContentType.JSON).when().delete("/rest/api/2/issue/"+issueId)
                .then().log().all().extract().response();
    }

}
