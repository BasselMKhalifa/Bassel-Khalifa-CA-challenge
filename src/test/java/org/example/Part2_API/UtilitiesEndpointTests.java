package org.example.Part2_API;
// Converted Tests for Utilities Endpoint
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UtilitiesEndpointTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetVersion() {
        Response response = given()
                .get("/utilities/version");

        response.then().statusCode(200)
                .body("version", notNullValue());
    }

    @Test
    public void testGetHealthCheck() {
        Response response = given()
                .get("/utilities/healthcheck");

        response.then().statusCode(200)
                .body("uptime", notNullValue())
                .body("readonly", notNullValue())
                .body("documents.products", notNullValue())
                .body("documents.stores", notNullValue())
                .body("documents.categories", notNullValue());
    }
}
