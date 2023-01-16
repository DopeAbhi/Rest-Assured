package files;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Basics {
    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response=given().queryParam("key","qaclick123").header("Content-type","application/json;charset=UTF-8")
                .body(new String(Files.readAllBytes(Paths.get("/home/abhay/Documents/addPlace.json")))) //read json from external file
                // .body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP")).
                header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath js=new JsonPath(response); //for parsing json
        String placeId=js.getString("place_id");
        System.out.println(placeId);

        //update Place
        String newAddress="70 Summer walk, USA";

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"name\": \"Test\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        //delete place
       /* given().log().all().queryParam("key","qaclick123")
                .body("{\n" +
                        "    \"place_id\":\""+placeId+"\"\n" +
                        "}\n")
                .when().delete("maps/api/place/delete/json")
                .then().assertThat().log().all().statusCode(200);*/

       //get place
        String getPlaceResponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

       /*JsonPath js1 =new JsonPath(getPlaceResponse);
       String actualAddress =js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);*/

        JsonPath js1= ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress=js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,"Pacific ocean");

    }
}


