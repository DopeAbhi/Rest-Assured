package main.java;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        String token=loginResponse.getToken();

        //Add Product

        RequestSpecification addProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization",loginResponse.getToken()).build();

        RequestSpecification reqAddProduct=  given().log().all().spec(addProductBaseReq).param("productAddedBy",loginResponse.getUserId())
                .param("productName","akshyshirts").
                param("productCategory","fashion").
                param("productSubCategory","shirts").
                param("productPrice","20000").
                param("productDescription","ashayshirtsoriginal").
                param("productFor","men").
                multiPart("productImage",new File("/home/abhay/Downloads/Test Data/ackn.png"));
        String addProductResponse=reqAddProduct.when().post("api/ecom/product/add-product")
                .then().extract().response().asString();

        JsonPath js=new JsonPath(addProductResponse);
        System.out.println(js.getString("message"));
        System.out.println(js.getString("productId"));
        String productId=js.getString("productId");


//Create Order

        RequestSpecification CreateOrderBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization",token).setContentType(ContentType.JSON).build();

        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(
                productId);

        List<OrderDetails> orderDetailsList=new ArrayList<OrderDetails>();
        orderDetailsList.add(orderDetails);

        Orders orders=new Orders();
        orders.setOrders(orderDetailsList);

        RequestSpecification CreateOrderReq= given().spec(CreateOrderBaseReq).body(orders);
        String res= CreateOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(res);


//Get Order Details
        RequestSpecification GetOrderDetailsReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization",token).setContentType(ContentType.JSON).build();


    }
}
