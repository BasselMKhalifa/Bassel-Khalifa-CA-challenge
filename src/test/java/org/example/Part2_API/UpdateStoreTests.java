package org.example.Part2_API;
// Converted Tests for Updating Stores
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateStoreTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testUpdateStoreName() {
        String storePayload = "{" +
                "\"name\": \"Retail Hub\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(storePayload)
                .post("/stores");

        String id = createResponse.then().extract().path("id");

        String updatedPayload = "{" +
                "\"name\": \"Updated Retail Hub\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/stores/" + id);

        updateResponse.then().statusCode(200)
                .body("name", equalTo("Updated Retail Hub"));
    }

    @Test
    public void testUpdateStoreNotFound() {
        String updatedPayload = "{" +
                "\"name\": \"Updated Retail Hub\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/stores/nonexistentId");

        updateResponse.then().statusCode(404);
    }
}
