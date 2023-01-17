package main.java;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public  static void main(String[] args)
    {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUserEmail("krivrm1520@gmail.com");
        loginRequest.setUserPassword("9838577522@kkK");

        RequestSpecification reqLogin=given().log().all().spec(req).body(loginRequest);
       LoginResponse loginResponse= reqLogin.when().post("/api/ecom/auth/login").then().extract()
               .response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getUserId());
        System.out.println(loginResponse.getMessage());
    }
}
