package org.example.Part2_API;
// Converted Tests for Updating Services
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateServiceTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testUpdateServiceName() {
        String servicePayload = "{" +
                "\"name\": \"Repair\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(servicePayload)
                .post("/services");

        String id = createResponse.then().extract().path("id");

        String updatedPayload = "{" +
                "\"name\": \"Updated Repair\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/services/" + id);

        updateResponse.then().statusCode(200)
                .body("name", equalTo("Updated Repair"));
    }

    @Test
    public void testUpdateServiceNotFound() {
        String updatedPayload = "{" +
                "\"name\": \"Updated Repair\"}";

        Response updateResponse = given()
                .contentType("application/json")
                .body(updatedPayload)
                .patch("/services/nonexistentId");

        updateResponse.then().statusCode(404);
    }
}
