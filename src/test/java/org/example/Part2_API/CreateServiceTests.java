package org.example.Part2_API;
// Converted Tests for Creating Services
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateServiceTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateServiceValidBody() {
        String servicePayload = "{" +
                "\"name\": \"Repair\"}";

        Response response = given()
                .contentType("application/json")
                .body(servicePayload)
                .post("/services");

        response.then().statusCode(201)
                .body("name", equalTo("Repair"));
    }

    @Test
    public void testCreateServiceMissingName() {
        String servicePayload = "{}";

        Response response = given()
                .contentType("application/json")
                .body(servicePayload)
                .post("/services");

        response.then().statusCode(400);
    }

    @Test
    public void testCreateServiceEmptyBody() {
        Response response = given()
                .contentType("application/json")
                .body("{}")
                .post("/services");

        response.then().statusCode(400);
    }
}
