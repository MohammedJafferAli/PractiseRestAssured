package org.day3;

/* In some scenarios we need to validate the information present in response headers,
 * To get the value of the header use the header name and methods from rest assured package
 *
 * */
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class WorkWithHeaders {


    @Test(priority = 1)
    void testSingleHeader() {

        given()

                .when()
                .get("https://google.com")

                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=ISO-8859-1") //Check the given header content-type and expected value match with the response at run time
                .log().headers(); //Log only the headers in the console
    }

    @Test(priority = 2)
    void testAllHeaderInResponse() {

        Response response = given() //Save the whole response in the variable Type Response which has other methods

                .when()
                .get("https://google.com");

        //Get a single header value from headers response
        System.out.println(response.getHeader("Date"));

        //Get all the headers in the response
        Headers allHeaders = response.headers();//Get all headers and store it into a response object where the values are stored in key & Value pair

        //Print all the headers - get the keys then values
        for (Header hd : allHeaders) {
            System.out.println(hd.getName() + "   " + hd.getValue());
        }
        //Note - This for loop can be replaced with the log().headers(); depends on the validation

    }
}
