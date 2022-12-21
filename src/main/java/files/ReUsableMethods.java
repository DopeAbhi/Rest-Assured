package files;
import io.restassured.path.json.JsonPath;
import jdk.dynalink.beans.StaticClass;

public class ReUsableMethods {
    public static JsonPath rawToJson(String getPlaceResponse)
    {
        JsonPath js1=new JsonPath("response");
        return js1;
    }
}
