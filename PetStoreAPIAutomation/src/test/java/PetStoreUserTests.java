import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PetStoreUserTests {

    @Test
    public void getUserInfo(){
        userCreate ();
        RestAssured
                .get ("https://petstore.swagger.io/v2/user/tester1234");

    }
    @Test
    public void userCreate(){
        Map<String ,Object > bodyPayLoad = new HashMap<>();
        bodyPayLoad.put ("id",123456790);
        bodyPayLoad.put ("username","tester1234");
        bodyPayLoad.put ("firstname","test212");
        bodyPayLoad.put ("Lastname","test2");
        bodyPayLoad.put ("email","test@mail.com");
        bodyPayLoad.put ( "password" ,"test123");
        bodyPayLoad.put ("phone","123456");
        bodyPayLoad.put ("userStatus",1);

        Map<String,String> header=new HashMap<> ();
        header.put ("accept","application/json");
        header.put ("content-Type","application/json");

        RestAssured
                .given ()
                .contentType (ContentType.JSON)
                .headers(header)
                .body (bodyPayLoad)
                .when ()
                .post ("https://petstore.swagger.io/v2/user")
                .then ()
                .statusCode (200);
    }
    @Test
    public void updateUser(){
        Map<String ,Object > bodyPayLoad = new HashMap<>();
        bodyPayLoad.put ("id",123456790);
        bodyPayLoad.put ("username","tester123456");
        bodyPayLoad.put ("firstname","test212");
        bodyPayLoad.put ("Lastname","test2");
        bodyPayLoad.put ("email","test@mail.com");
        bodyPayLoad.put ( "password" ,"test123");
        bodyPayLoad.put ("phone","123456");
        bodyPayLoad.put ("userStatus",1);

        RestAssured
                .given ()
                .contentType ("application/json")
                .body (bodyPayLoad)
                .when ()
                .put ("https://petstore.swagger.io/v2/user/tester1234")
                .then ()
                .statusCode (200);

    }
    @Test
    public void deleteUser(){
        userCreate ();

        RestAssured
                .delete ("https://petstore.swagger.io/v2/user/tester1234")
                .then ()
                .statusCode (200);
    }

    @Test
    public void userlogin(){
        Map<String, String> queryParamsValue= new HashMap<> ();
        queryParamsValue.put ("username","hasankurtay");
        queryParamsValue.put ("password","12345678");


        RestAssured
                .given ()
                .queryParams (queryParamsValue)
                .when ()
                .get ( "https://petstore.swagger.io/v2/user/login")
                .then ()
                .statusCode (200);
    }

    @Test
    public void userLogout(){
        RestAssured
                .get ( "https://petstore.swagger.io/v2/user/logout" )
                .then ()
                .statusCode (200);
    }


    @Test
    public void userCreateWithArray(){
        String payLoad = "  {\n" +
                "    \"id\": 312123,\n" +
                "    \"username\": \"testing\",\n" +
                "    \"firstName\": \"testing\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"email\": \"mail@yok.com\",\n" +
                "    \"password\": \"123457\",\n" +
                "    \"phone\": \"05444444444\",\n" +
                "    \"userStatus\": 1\n" +
                "  }\n" +
                ",\n" +
                "{\n" +
                "    \"id\": 3121231,\n" +
                "    \"username\": \"testing1\",\n" +
                "    \"firstName\": \"testing1\",\n" +
                "    \"lastName\": \"test1\",\n" +
                "    \"email\": \"mail1@yok.com\",\n" +
                "    \"password\": \"1234571\",\n" +
                "    \"phone\": \"054444444441\",\n" +
                "    \"userStatus\": 2\n" +
                "  }";
        RestAssured
                .given ()
                .when ()
                .post ( "https://petstore.swagger.io/v2/user/createWithArray")
                .then ()
                .statusCode (415);
    }

    @Test
    public void userCreateWithList(){
        String payLoad = "[\n" +
                "  {\n" +
                "    \"id\": 312123,\n" +
                "    \"username\": \"testing\",\n" +
                "    \"firstName\": \"testing\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"email\": \"mail@yok.com\",\n" +
                "    \"password\": \"123457\",\n" +
                "    \"phone\": \"05444444444\",\n" +
                "    \"userStatus\": 1\n" +
                "  }\n" +
                ",\n" +
                "{\n" +
                "    \"id\": 3121231,\n" +
                "    \"username\": \"testing1\",\n" +
                "    \"firstName\": \"testing1\",\n" +
                "    \"lastName\": \"test1\",\n" +
                "    \"email\": \"mail1@yok.com\",\n" +
                "    \"password\": \"1234571\",\n" +
                "    \"phone\": \"054444444441\",\n" +
                "    \"userStatus\": 2\n" +
                "  }\n" +
                "\n" +
                "]";
        RestAssured
                .given ()
                .body (payLoad)
                .contentType ("application/json")
                .when ()
                .post ( "https://petstore.swagger.io/v2/user/createWithList" )
                .then ()
                .statusCode (200);
    }
}
