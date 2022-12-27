package main.java;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SumValidation {

    @Test
    public void sumOfCourses() {
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size()");
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum = sum + js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies");
        }
        int dasboard = js.getInt("dashboard.purchaseAmount");

        Assert.assertEquals(sum, dasboard);
        System.out.println("Total Price " + sum);

    }

    }
