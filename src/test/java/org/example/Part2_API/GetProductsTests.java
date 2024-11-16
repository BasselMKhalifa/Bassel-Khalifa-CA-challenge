package org.example.Part2_API;
// Converted Tests for Getting All Products
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetProductsTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetProductsSuccess() {
        Response response = given()
                .get("/products");

        response.then().statusCode(200);
    }

    @Test
    public void testGetProductsTotalCount() {
        Response response = given()
                .get("/products");

        response.then().statusCode(200)
                .body("total", instanceOf(Integer.class));
    }
}
