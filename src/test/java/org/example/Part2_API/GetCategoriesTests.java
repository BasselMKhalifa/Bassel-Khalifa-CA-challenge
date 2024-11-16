package org.example.Part2_API;
// Converted Tests for Getting Categories
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCategoriesTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetCategoriesSuccess() {
        Response response = given()
                .get("/categories");

        response.then().statusCode(200);
    }

    @Test
    public void testGetCategoriesTotalCount() {
        Response response = given()
                .get("/categories");

        response.then().statusCode(200)
                .body("total", instanceOf(Integer.class));
    }
}
