package org.example.Part2_API;
// Converted Tests for Deleting Products
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteProductTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteProductValidId() {
        String productPayload = "{" +
                "\"name\": \"Chair\"," +
                "\"type\": \"Furniture\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(productPayload)
                .post("/products");

        String id = createResponse.then().extract().path("id");

        Response deleteResponse = given()
                .delete("/products/" + id);

        deleteResponse.then().statusCode(200);
    }

    @Test
    public void testDeleteProductInvalidId() {
        Response response = given()
                .delete("/products/invalidId");

        response.then().statusCode(404);
    }
}
