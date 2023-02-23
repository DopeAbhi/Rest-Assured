package main.java;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public  static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("krivrm1520@gmail.com");
        loginRequest.setUserPassword("9838577522@kkK");

        RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract()
                .response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getUserId());
        System.out.println(loginResponse.getMessage());
        String token = loginResponse.getToken();

        //Add Product

        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", loginResponse.getToken()).build();

        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productAddedBy", loginResponse.getUserId())
                .param("productName", "akshyshirts").
                param("productCategory", "fashion").
                param("productSubCategory", "shirts").
                param("productPrice", "20000").
                param("productDescription", "ashayshirtsoriginal").
                param("productFor", "men").
                multiPart("productImage", new File("/Users/abhayverma/IdeaProjects/Rest-Assured/src/main/java/main/java/download.jpeg"));
        String addProductResponse = reqAddProduct.when().post("api/ecom/product/add-product")
                .then().extract().response().asString();

        JsonPath js = new JsonPath(addProductResponse);
        System.out.println(js.getString("message"));
        System.out.println(js.getString("productId"));
        String productId = js.getString("productId");


//Create Order
        System.out.println(token);
        RequestSpecification CreateOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON).build();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(
                productId);

        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        orderDetailsList.add(orderDetails);

        Orders orders = new Orders();
        orders.setOrders(orderDetailsList);


        RequestSpecification CreateOrderReq = given().log().all().spec(CreateOrderBaseReq).body(orders);
        String res = CreateOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(res);
        JsonPath jsonPath = new JsonPath(res);
        String order = jsonPath.getString("orders");
        String orderId=order.substring(1,25);
        System.out.println(orderId);


//Get Order Details


        RequestSpecification GetorderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                setUrlEncodingEnabled(false)

                .addHeader("authorization", token).setContentType(ContentType.JSON).build();

     ResponseSpecification responseSpecification=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

     RequestSpecification requestSpecification = given().log().all().queryParam("id",orderId).
             spec(GetorderBaseReq);
                GetOrderDetails getOrderDetails=requestSpecification.when().get("/api/ecom/order/get-orders-details")
                .then().spec(responseSpecification).assertThat().log().all().extract()
                        .response().as(GetOrderDetails.class);

        System.out.println(getOrderDetails.getData().getOrderById());








//Delete Product

        RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON).build();

        RequestSpecification proddel=given().spec(deleteProdBaseReq).log().all().pathParam("productId",productId);
        String ress=proddel.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().extract().response().asString();
        JsonPath jsonPath1=new JsonPath(ress);
        Assert.assertEquals("Product Deleted Successfully",jsonPath1.get("message"));

        System.out.println(ress);




    }}