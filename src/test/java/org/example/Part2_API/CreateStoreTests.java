package org.example.Part2_API;
// Converted Tests for Creating Stores
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateStoreTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateStoreValidBody() {
        String storePayload = "{" +
                "\"name\": \"Retail Hub\"," +
                "\"type\": \"Store\"," +
                "\"address\": \"123 Main St\"," +
                "\"city\": \"Springfield\"," +
                "\"state\": \"IL\"," +
                "\"zip\": \"62701\"}";

        Response response = given()
                .contentType("application/json")
                .body(storePayload)
                .post("/stores");

        response.then().statusCode(201)
                .body("name", equalTo("Retail Hub"))
                .body("type", equalTo("Store"));
    }

    @Test
    public void testCreateStoreMissingName() {
        String storePayload = "{" +
                "\"type\": \"Store\"," +
                "\"address\": \"123 Main St\"}";

        Response response = given()
                .contentType("application/json")
                .body(storePayload)
                .post("/stores");

        response.then().statusCode(400);
    }

    @Test
    public void testCreateStoreEmptyBody() {
        Response response = given()
                .contentType("application/json")
                .body("{}")
                .post("/stores");

        response.then().statusCode(400);
    }
}
