package main.java;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size ()");
        System.out.println(count);
        //Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        //Print Title of the Course
        String title = js.getString("courses[1].title");
        System.out.println(title);

        //Print all courses title and thier respective prices

        for (int i = 0; i < count; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            System.out.println(js.get("courses[" + i + "].price ").toString());
            System.out.println(courseTitle);
        }

        //Print no. of copies of specific course
        for (int i = 0; i < count; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copies = js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
        // Sum of Price Equals to the Purchase Amount
        int sum=0;
        for (int i=0; i<count; i++)
        {
             sum= sum + js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
        }
        int dasboard=js.getInt("dashboard.purchaseAmount");

        if (dasboard == sum)
        {
            System.out.println(sum);
        }
    }



}