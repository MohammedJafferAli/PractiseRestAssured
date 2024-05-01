package org.day1;


/*Static imports for rest-assured testing*/

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

/*By default, rest assured supports cucumber format, so write the test in the given below format
 *
 *    given()
 *   .when()
 *   .then()
 *
 * */

// Practise URL for rest api testing  - https://reqres.in/

public class HTTPRequests {

    int id;

    @Test(priority = 1)
    void getUsers() {

        System.out.println("Getting list of users... ");
        given() // Add any prerequisite for this request if any

                .when() //Send the request header
                .get("https://reqres.in/api/users?page=2")

                .then() //Add assertion
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

    }


    @Test(priority = 2)
    void createUser() {
        HashMap data = new HashMap();
        data.put("name", "Mohammed");
        data.put("job", "SDET");

        System.out.println("Creating users... ");

        id = given()
                .contentType("application/json")
                .body(data)

                .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");


        System.out.println("User created successfully -  ID: " + id);

           /* .then()
                .statusCode(201);
*/
    }


    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap data = new HashMap();
        data.put("name", "Jaffer");
        data.put("job", "QA Lead");

        System.out.println("Updating user details with new data "+data);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                    .put("https://reqres.in/api/users"+id)

                .then()
                    .statusCode(200)
                    .log().all();



    }

}
