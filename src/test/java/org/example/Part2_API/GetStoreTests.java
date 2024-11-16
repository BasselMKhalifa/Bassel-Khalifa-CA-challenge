package org.example.Part2_API;
// Converted Tests for Getting a Single Store by ID
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetStoreTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetStoreByIdSuccess() {
        String storePayload = "{" +
                ""name": "Retail Hub"," +
                ""type": "Store"," +
                ""address": "123 Main St"," +
                ""city": "Springfield"," +
                ""state": "IL"," +
                ""zip": "62701"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(storePayload)
                .post("/stores");

        String id = createResponse.then().extract().path("id");

        Response getResponse = given()
                .get("/stores/" + id);

        getResponse.then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Retail Hub"));
    }
}
