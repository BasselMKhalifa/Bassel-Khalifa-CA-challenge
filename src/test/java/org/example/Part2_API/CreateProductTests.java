package org.example.Part2_API;
// Converted Tests for Creating Products
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateProductTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateProductValidBody() {
        String productPayload = "{" +
                "\"name\": \"Laptop\"," +
                "\"type\": \"Electronics\"," +
                "\"upc\": \"12345\"," +
                "\"description\": \"High-end laptop\"," +
                "\"model\": \"X123\"}";

        Response response = given()
                .contentType("application/json")
                .body(productPayload)
                .post("/products");

        response.then().statusCode(201)
                .body("name", equalTo("Laptop"))
                .body("type", equalTo("Electronics"));
    }

    @Test
    public void testCreateProductMissingName() {
        String productPayload = "{" +
                "\"type\": \"Electronics\"," +
                "\"upc\": \"12345\"," +
                "\"description\": \"High-end laptop\"," +
                "\"model\": \"X123\"}";

        Response response = given()
                .contentType("application/json")
                .body(productPayload)
                .post("/products");

        response.then().statusCode(400);
    }

    @Test
    public void testCreateProductEmptyBody() {
        Response response = given()
                .contentType("application/json")
                .body("{}")
                .post("/products");

        response.then().statusCode(400);
    }
}
