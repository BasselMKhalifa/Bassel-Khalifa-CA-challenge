package org.example.Part2_API;
// Converted Tests for Updating Categories
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateCategoryTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testUpdateCategoryName() {
        String categoryPayload = "{" +
                "\"name\": \"Electronics\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        String id = createResponse.then().extract().path("id");

        String updatedPayload = "{" +
                "\"name\": \"Updated Electronics\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/categories/" + id);

        updateResponse.then().statusCode(200)
                .body("name", equalTo("Updated Electronics"));
    }

    @Test
    public void testUpdateCategoryNotFound() {
        String updatedPayload = "{" +
                "\"name\": \"Updated Electronics\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/categories/nonexistentId");

        updateResponse.then().statusCode(404);
    }
}
