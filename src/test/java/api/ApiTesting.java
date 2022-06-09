package api;

import com.restassured.util.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class ApiTesting extends Hook{


    public void add_event_with_token() {
        Response response = RestAssured.given().spec(Utils.create_request_specfication("token")).body(Utils.jsonFile_Retrievers("data")).when().post("/v1/event/create").then().extract().response();
        Assert.assertEquals(response.statusCode(),200);
    }

    public void add_event_with_token_and_without_body() {
        Response response = RestAssured.given().spec(Utils.create_request_specfication("token")).when().post("/v1/event/create").then().extract().response();
        Assert.assertEquals(response.statusCode(),400);
    }

    public void add_event_with_token_and_invalid_format_body() {
        int status = RestAssured.given().spec(Utils.create_request_specfication("token")).body(Utils.jsonFile_Retrievers("invalidData")).when().post("/v1/event/create").then().extract().response().statusCode();
        Assert.assertEquals(status,400);
    }

    public void add_event_with_token_and_invalid_body() {
        int status = RestAssured.given().spec(Utils.create_request_specfication("token")).body(Utils.jsonFile_Retrievers("invalidData")).when().post("/v1/event/create").then().extract().response().statusCode();
        Assert.assertEquals(status,400);
    }

    public void add_event_without_token_and_with_valid_body() {
        int status = RestAssured.given().spec(Utils.create_request_specfication("withoutToken")).body(Utils.jsonFile_Retrievers("data")).when().post("/v1/event/create").then().extract().response().statusCode();
        Assert.assertEquals(status,401);
    }

    public void add_event_without_token_and_with_invalid_body() {
        int status = RestAssured.given().spec(Utils.create_request_specfication("withoutToken")).body(Utils.jsonFile_Retrievers("data")).when().post("/v1/event/create").then().extract().response().statusCode();
        Assert.assertEquals(status,401);
    }


}


