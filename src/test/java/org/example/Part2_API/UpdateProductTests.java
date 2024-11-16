package org.example.Part2_API;
// Converted Tests for Updating Products
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateProductTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testUpdateProductName() {
        String productPayload = "{" +
                "\"name\": \"Laptop\"," +
                "\"type\": \"Electronics\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(productPayload)
                .post("/products");

        String id = createResponse.then().extract().path("id");

        String updatedPayload = "{" +
                "\"name\": \"Updated Laptop\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/products/" + id);

        updateResponse.then().statusCode(200)
                .body("name", equalTo("Updated Laptop"));
    }

    @Test
    public void testUpdateProductNotFound() {
        String updatedPayload = "{" +
                "\"name\": \"Updated Laptop\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/products/nonexistentId");

        updateResponse.then().statusCode(404);
    }
}
