package main.java;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class JiraApi {
    public static void main(String[] args) throws IllegalArgumentException
    {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter session= new SessionFilter();
   String res=given().header("Content-Type","application/json;charset=UTF-8").body("{ \"username\": \"krivrm1520\", \"password\": \"9838577522\" }").log().all()
           .filter(session)
           .when().post("/rest/auth/1/session").then().extract().response().asString();
        System.out.println(res);
       /* JsonPath js=new JsonPath(res);
        String sessionid= js.getString("session.value");
        String value= js.getString("session.name");
        String test=value+sessionid;
*/

        given().pathParam("key","10103").header("Content-Type","application/json;charset=UTF-8").log().all().body("{\n" +
                "    \"body\": \"Updating Existingz.\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().assertThat().log().all().statusCode(201);


    }

}
