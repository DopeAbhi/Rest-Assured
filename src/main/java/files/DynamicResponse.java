package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DynamicResponse
{
    @Test(dataProvider = "BooksData")

    public void addBook(String isbn,String aisle) throws IllegalArgumentException{
        RestAssured.baseURI = "http://216.10.245.166";
        String res = given().log().all().header("Content-Type", "application/json")
                .body(Payload.AddBook(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(res);
        String output = js.getString("ID");


        //delete record
             given().log().all()
                .body("{\n" +
                        " \n" +
                        "\"ID\" : \""+output+"\"\n" +
                        " \n" +
                        "")
                .when().delete("/Library/DeleteBook.php")
                     .then().log().all().assertThat().statusCode(200).body("msg",equalTo("book is successfully deleted"));
        System.out.println(output);
    }





    @DataProvider(name = "BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"eqqaqp","1396"},{"pqqios","1390"},{"IjrotP","1805"}}; //change data every time before run
    }


    }
