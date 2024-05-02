package org.day1;


/*There are 4 different ways to send the body in the POST request, there are
*
*  1. Map collection - HashMap
*  2. json library
*  3. POJO class - Plain Old Java Object - create separate class
*  4. External json file
*
* */

/*Static imports for rest-assured testing*/

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

public class PostRequestTypes {



    //Post request body using HashMap - Preferrable for small data
   @Test(priority = 1)
    void testPostRequestUsingHashMap()
    {
        HashMap data = new HashMap();
        data.put("name","Rutten");
        data.put("location","US");
        data.put("phone", "1234567");

       String courseArr[] = {"Java","Selenium"};
       data.put("courses",courseArr);


       given()
               .contentType("application/json")
               .body(data)

               .when()
               .post("http://localhost:3000/students")

               .then()
               .statusCode(201)
               .body("name",equalTo("Rutten"))
               .body("location",equalTo("US"))
               .body("phone",equalTo("1234567"))
               .body("courses[0]",equalTo("Java"))
               .body("courses[1]",equalTo("Selenium"))
               .header("Content-type","application/json")
               .log().all();
    }

    @Test(priority = 2)
    void testDeleteRecords()
    {
        given()

                .when()
                .delete("http://localhost:3000/students/3")

                .then()
                .statusCode(200);

    }


}

