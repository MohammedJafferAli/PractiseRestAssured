package day2;

/*There are 4 different ways to send the body in the POST request, there are
 *
 *  1. Map collection - HashMap
 *  2. json library
 *  3. POJO class - Plain Old Java Object - create separate class
 *  4. External json file
 *
 * */

/*Static imports for rest-assured testing*/

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class DifferentPostRequest {

    //@Test(priority = 1)
    void testJSONPostRequest() {
        JSONObject data = new JSONObject(); //Create JSON Object
        data.put("name", "Eric");
        data.put("location", "US");
        data.put("phone", "1234567");

        String[] courseArr = {"Java", "Selenium"};
        data.put("courses", courseArr);


        given()
                .contentType("application/json")
                .body(data.toString()) //Convert the json to string before passing

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Eric"))
                .body("location", equalTo("US"))
                .body("phone", equalTo("1234567"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Selenium"))
                .header("Content-type", "application/json")
                .log().all();
    }

    //@Test(priority = 2)
    void testPOJOPostRequest() {

        POJOJsonData data = new POJOJsonData();

        data.setName("Blossom");
        data.setLocation("US");
        data.setPhone("1234567");

        String[] courseArr = {"RestAssured", "Testng"};
        data.setCourses(courseArr);


        given()
                .contentType("application/json")
                .body(data) //Convert the json to string before passing

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Blossom"))
                .body("location", equalTo("US"))
                .body("phone", equalTo("1234567"))
                .body("courses[0]", equalTo("RestAssured"))
                .body("courses[1]", equalTo("Testng"))
                .header("Content-type", "application/json")
                .log().all();

    }
    @Test(priority = 4)
    void testDeleteRecords() {
        given()

                .when()
                .delete("http://localhost:3000/students/3")

                .then()
                .statusCode(200);

    }

    @Test(priority = 3)
    void testExternalFilePostRequest() throws FileNotFoundException {

        File jsonfile = new File(".//Documents/body.json"); // Pointing to the file source
        FileReader fr = new FileReader(jsonfile); //pass the file to file reader

        JSONTokener jt = new JSONTokener(fr); //Tokener convert the attributes into JSON objects
        JSONObject data = new JSONObject(jt); // Convert into JSON object

        given()
                .contentType("application/json")
                .body(data.toString()) //Convert the json to string before passing

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Smith"))
                .body("location", equalTo("Los Angeles"))
                .body("phone", equalTo("567-8901"))
                .body("courses[0]", equalTo("Biology"))
                .body("courses[1]", equalTo("Chemistry"))
                .header("Content-type", "application/json")
                .log().all();

    }
}
