package api;

import com.restassured.util.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Hook {

    @BeforeClass
    public void get_token(){
        String result = RestAssured.given().spec(Utils.create_request_specfication("token")).body(Utils.jsonFile_Retrievers("token")).when().post("/oauth/token").then().extract().response().asString();
        Utils.set_element_to_properties_file("token",Utils.json_parser(result,"access_token"));
    }
}
