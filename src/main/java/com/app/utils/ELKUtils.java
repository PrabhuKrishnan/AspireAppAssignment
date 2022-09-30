package com.app.utils;

import com.app.config.PropertyConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public final class ELKUtils {

    private ELKUtils(){}

    public static void sendDetailsToELK(String testName,String status) {
        if (PropertyConfigReader.getConfig().sendResultsToELK().equalsIgnoreCase("yes")) {
            HashMap<String, String> map = new HashMap<>();
            map.put("testName", testName);
            map.put("Status", status);
            map.put("executionTime", LocalDateTime.now().toString());

            Response response = given().header("Content-Type", "application/json")
                    .log()
                    .all()
                    .body(map)
                    .post(PropertyConfigReader.getConfig().elasticURL());

            response.prettyPrint();
            Assert.assertEquals(response.statusCode(), 201);
        }
    }


}
