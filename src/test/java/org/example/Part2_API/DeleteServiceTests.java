package org.example.Part2_API;
// Converted Tests for Deleting Services
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteServiceTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteServiceValidId() {
        String servicePayload = "{" +
                "\"name\": \"Cleaning\"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(servicePayload)
                .post("/services");

        String id = createResponse.then().extract().path("id");

        Response deleteResponse = given()
                .delete("/services/" + id);

        deleteResponse.then().statusCode(200);
    }

    @Test
    public void testDeleteServiceInvalidId() {
        Response response = given()
                .delete("/services/invalidId");

        response.then().statusCode(404);
    }
}
