package Pojo;

import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Addplace p = new Addplace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number(983833888);
        p.setWebsite("https://rehulshettyacademy.com");
        p.setName("Frontline House");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location Lo = new Location();
        Lo.setLat(-38.383494);
        Lo.setLng(33.427362);


        p.setLocation(Lo);


        String res = given().queryParam("key", "qaclick123").log().all().body(p)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println(res);
    }
}

