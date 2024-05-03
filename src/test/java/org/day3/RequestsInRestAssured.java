package org.day3;

/*
 *  Sometimes, user has to send the path and query value along with the header
 * in order to identify the resources uniquely and effectively. Where in postman the can be done by creating path variable
 * and query value based on the API design.
 *  In rest assured, there are methods available to do the same
 *
 *
 * */

/*Static imports for rest-assured testing*/

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

public class RequestsInRestAssured {

    @Test(priority = 1)
    void testQueryAndPathParam() {

        given()
                .pathParam("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 11)

                .when()
                .get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 2)
    void testCookies() {
        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC", "AQTF6Hyqc2Mk15RVpWbKuJLvWV4AB-pEKXqGrtQZLuL9jEPLvIfwB2buMhI")
                .log().all();
    }

    @Test(priority = 3)
    void testCookiesWithVar() {

        Response response = given()

                .when()
                .get("https://www.google.com/");

        //Get Single cookie value
        String AEC = response.getCookie("AEC");
        System.out.println("Cookie value of AEC  " + AEC);

        //Get multiple cookies value in Key and Value pair
        Map<String, String> cookie_values = response.getCookies();

        //System.out.println(cookie_value.keySet());
        for (String key : cookie_values.keySet())//Extract only the key
        {
            String cookie_value = response.getCookie(key);
            System.out.println("Key " + key + "  " + cookie_value);
        }

    }


}
