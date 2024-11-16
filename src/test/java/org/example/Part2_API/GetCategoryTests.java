package org.example.Part2_API;
// Converted Tests for Getting a Single Category by ID
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCategoryTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetCategoryByIdSuccess() {
        String categoryPayload = "{" +
                ""id": "cat123"," +
                ""name": "Electronics"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(categoryPayload)
                .post("/categories");

        String id = createResponse.then().extract().path("id");

        Response getResponse = given()
                .get("/categories/" + id);

        getResponse.then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Electronics"));
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        Response response = given()
                .get("/categories/invalidId");

        response.then().statusCode(404);
    }
}
