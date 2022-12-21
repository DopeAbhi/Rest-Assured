package main.java;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[]args)
    {
        JsonPath js =new JsonPath(Payload.CoursePrice());
    }
}