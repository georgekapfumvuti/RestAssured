package com.restassured.util;
import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;


public class Utils {
    static Date date = new Date();
    static  PrintStream log;

    public static RequestSpecification create_request_specfication(String request) {

        if(request.equals("create"))
                return new RequestSpecBuilder()
                        .setBaseUri(get_element_from_global_properties("BaseUrl"))
                        .setContentType(ContentType.JSON).build();

        else if(request.equals("withoutToken")) return new RequestSpecBuilder()
                                                        .setBaseUri(get_element_from_global_properties("BaseUrl"))
                                                        .setUrlEncodingEnabled(false)
                                                        .addHeader("Accept","application.json")
                                                        .setContentType(ContentType.JSON).build();
        else return new RequestSpecBuilder()
                    .setBaseUri(get_element_from_global_properties("BaseUrl"))
                    .setUrlEncodingEnabled(false)
                    .addHeader("Authorization","Bearer "+get_element_from_global_properties("token"))
                    .addHeader("Accept","application.json")
                    .setContentType(ContentType.JSON).build();

    }

    public static void set_element_to_properties_file(String key, String value) {

        Properties property = new Properties();
        try {
            FileInputStream readMe = new FileInputStream
                    ("src/main/resources/global.properties");
            property.load(readMe);
            property.setProperty(key, value);
            OutputStream outputStream = new FileOutputStream("src/main/resources/global.properties");
            property.store(outputStream, date.toString());
        } catch (IOException exception) {
            System.out.println("Something gone wrong in Utils. set_element_to_properties_file " + exception.getMessage());
        }

    }

    public static String get_element_from_global_properties(String key) {
        Properties property = new Properties();
        try {
            FileInputStream readMe = new FileInputStream("src/main/resources/global.properties");
            property.load(readMe);
        } catch (IOException exception) {
            System.out.println("Something gone wrong in Utils. get_element_from_properties_file method " + exception.getMessage());
        }

        return property.getProperty(key);

    }

    public static String  json_parser(String response, String key) {
        JsonPath js = new JsonPath(response);
        return js.get(key).toString();
    }


    public static File jsonFile_Retrievers(String filename) {
        return new File("src/main/java/com/restassured/file/"+filename+".json");
    }

}
