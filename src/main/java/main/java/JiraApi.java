package main.java;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;
import static io.restassured.RestAssured.*;

public class JiraApi {
    public static void main(String[] args)
    {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter session= new SessionFilter();
   String res=given().header("Content-Type","application/json;charset=UTF-8").body("{ \"username\": \"krivrm1520\", \"password\": \"9838577522@kkk\" }").log().all()
           .filter(session)
           .when().post("/rest/auth/1/session").then().extract().response().asString();
        System.out.println(res);
       /* JsonPath js=new JsonPath(res);
        String sessionid= js.getString("session.value");
        String value= js.getString("session.name");
        String test=value+sessionid;
*/
        String expectedMessage="Hi How are you";

        //add comment

      String commentresponse=  given().pathParam("key","10001").header("Content-Type","application/json;charset=UTF-8").log().all().body("{\n" +
                "    \"body\": \""+expectedMessage+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().assertThat().log().all().statusCode(201).extract().asString();
      JsonPath js=new JsonPath(commentresponse);
      String commentId= js.getString("id");

        //Add Attachment
     /*   given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key","10001")
                .header("Content-Type","multipart/form-data")
                .multiPart("file",new File("test.html")).when()
                .post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);*/

        //Get Issue
     String tes=   given().filter(session).pathParam("key",10001).queryParam("fields","comment").when().get("/rest/api/2/issue/{key}").then()
                .log().all().extract().response().asString();
        System.out.println(tes);

        JsonPath js1= new JsonPath(tes);
        int commentCount=js1.getInt("fields.comment.comments.size()");
        for (int i = 0; i < commentCount ; i++) {

            String commentIdIssue=js1.get("fields.comment.comments["+i+"].id").toString();
            if (commentIdIssue.equalsIgnoreCase(commentId))
            {
              String message=  js1.get("fields.comment.comments["+i+"].body").toString();
                Assert.assertEquals(message,expectedMessage);
                System.out.println(message);
            }

        }
        


    }

}

