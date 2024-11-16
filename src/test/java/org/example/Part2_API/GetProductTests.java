package org.example.Part2_API;
// Converted Tests for Getting a Single Product by ID
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetProductTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetProductByIdSuccess() {
        String productPayload = "{" +
                ""name": "Laptop"," +
                ""type": "Electronics"," +
                ""upc": "12345"," +
                ""description": "High-end laptop"," +
                ""model": "X123"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(productPayload)
                .post("/products");

        String id = createResponse.then().extract().path("id");

        Response getResponse = given()
                .get("/products/" + id);

        getResponse.then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Laptop"));
    }

    @Test
    public void testGetProductByIdNotFound() {
        Response response = given()
                .get("/products/invalidId");

        response.then().statusCode(404);
    }
}
