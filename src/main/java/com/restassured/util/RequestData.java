package com.restassured.util;

import java.util.HashMap;

public class RequestData {
    public static HashMap<String,String> get_token_body() {
        return new HashMap<String, String>() {{
            put("client_id", Utils.get_element_from_global_properties("client_id"));
            put("client_secret", Utils.get_element_from_global_properties("client_secret"));
            put("audience", "https://www.introvoke.com/api");
            put("grant_type", "client_credentials");
        }};
    }
}
