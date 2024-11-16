package org.example.Part2_API;
// Converted Tests for Deleting Categories
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteCategoryTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteCategoryValidId() {
        String categoryPayload = "{" +
                "\"id\": \"cat456\"," +
                "\"name\": \"Furniture\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        String id = createResponse.then().extract().path("id");

        Response deleteResponse = given()
                .delete("/categories/" + id);

        deleteResponse.then().statusCode(200);
    }

    @Test
    public void testDeleteCategoryInvalidId() {
        Response response = given()
                .delete("/categories/invalidId");

        response.then().statusCode(404);
    }
}
