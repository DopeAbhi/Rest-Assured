package main.java;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static io.restassured.RestAssured.given;

public class oAuthTest {
    public static void main(String[] args) throws InterruptedException {
    //    System.setProperty("webdriver.chrome.driver","/home/abhay/Downloads/chromedriver_linux64/chromedriver");
      /*  WebDriver driver= new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/getCourse.php?state=verify&code=4/0AWgavddSPBNFpvkdLYE__q32s9Dp6ZxpI47bGrYfYEWi1EUYR1SZkyqLOg7bAuiffAT1eQ&scope=email%20https://www.googleapis.com/auth/userinfo.email%20openid&authuser=0&hd=w3villa.com&prompt=consent");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("krivrm1520");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("9838577522@kkk");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
       // String url=driver.getCurrentUrl(); */
        String url="https://rahulshettyacademy.com/getCourse.php?state=verify&code=4%2F0AWgavdcuPNLYTZXndo4_sCCl0y4bexeBq63FHHvUcaoiICUqdBaHAGrq3zQCHAIt1n2Tlw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
        String partialcode=url.split("code=")[1];
        String code=partialcode.split("&scope")[0];
        System.out.println(code);



        String accessTokenResponse=given().urlEncodingEnabled(false).
                queryParams("code","code")
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js=new JsonPath(accessTokenResponse);
        String accessToken=js.getString("access_token");



        String response=given().queryParam("access_token",accessToken).when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);

    }

}
