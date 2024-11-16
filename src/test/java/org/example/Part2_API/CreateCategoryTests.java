package org.example.Part2_API;
// Converted Tests for Creating Categories
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateCategoryTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateCategoryValidBody() {
        String categoryPayload = "{" +
                "\"id\": \"cat123\"," +
                "\"name\": \"Electronics\"}";

        Response response = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        response.then().statusCode(201)
                .body("id", equalTo("cat123"))
                .body("name", equalTo("Electronics"));
    }

    @Test
    public void testCreateCategoryMissingName() {
        String categoryPayload = "{" +
                "\"id\": \"cat123\"}";

        Response response = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        response.then().statusCode(400);
    }

    @Test
    public void testCreateCategoryMissingId() {
        String categoryPayload = "{" +
                "\"name\": \"Electronics\"}";

        Response response = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        response.then().statusCode(400);
    }

    @Test
    public void testCreateCategoryEmptyBody() {
        Response response = given()
                .contentType("application/json")
                .body("{}")
                .post("/categories");

        response.then().statusCode(400);
    }
}
