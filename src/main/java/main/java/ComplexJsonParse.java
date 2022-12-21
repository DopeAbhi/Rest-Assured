package main.java;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[]args)
    {
        JsonPath js =new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size ()");
        System.out.println(count);
        int totalAmount= js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        String title=js.getString("courses[1].title");
        System.out.println(title);

    }
}