package org.day4;

/* This class helps to understand how to work with response body with different approaches
 * 1. When there are only few things to validate in the response - use methods in the "then" section
 * 2. When there are many things to validate - use JSONObject class and it's methods to validate
 * */

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class WorkWithResponses {


    @Test(priority = 1)
    //Approach 1 - Needs to validate few objects in the response
    void testResponseBody() {
        given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/book")

                .then()
                .statusCode(200)
                .header("content-type", "application/json")
                .body("title[2]", equalTo("To Kill a Mockingbird"));

//         .log().headers();
    }

    @Test(priority = 2)
    void testResponseWithAssertion() {

        Response response = given() //Store whole response into response object
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/book");

        //System.out.println(response.header("content-type"));

        //Since we have the whole response , can validate which ever the section we want
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.header("content-type"), "application/json");

        String booktitle = response.jsonPath().get("title[2]").toString(); //Specify path in get method and convert into String
        Assert.assertEquals(booktitle, "To Kill a Mockingbird");

    }

    @Test(priority = 3)
    //Approach 2 to print title and total price
    void testPrintAllTitle() {
        Response response = given() //Store whole response into response object
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/book");

        //To traverse in the response body , especially when we have complex json file and the path is dynamic
        //then we should identify the path then validate the value

        JSONObject jo = new JSONObject(response.asString()); //coverting the whole response to JSONObject - use asString

        //Print the title of all books
        for (int init = 0; init < jo.getJSONArray("book").length(); init++) {
            // To save title in string get the array first, find the objects only by one by passing index, use get to retrieve specific element then convert into string
            String booktitle = jo.getJSONArray("book").getJSONObject(init).get("title").toString();
            //System.out.println(booktitle);
            Boolean status = false;
            if (booktitle.equals("1984")) {
                status = true;
                break;
            }

        }
        //Find the total price of all books
        Double total_price = 0d;
        for (int init = 0; init < jo.getJSONArray("book").length(); init++) {
            // To save title in string get the array first, find the objects only by one by passing index, use get to retrieve specific element then convert into string
            String book_price = jo.getJSONArray("book").getJSONObject(init).get("price").toString();
            total_price = total_price + Double.parseDouble(book_price);

        }
        System.out.println(total_price);

    }
}
