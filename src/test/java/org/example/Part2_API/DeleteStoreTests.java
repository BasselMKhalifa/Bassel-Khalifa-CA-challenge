package org.example.Part2_API;
// Converted Tests for Deleting Stores
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteStoreTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteStoreValidId() {
        String storePayload = "{" +
                ""name": "Retail Hub"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(storePayload)
                .post("/stores");

        String id = createResponse.then().extract().path("id");

        Response deleteResponse = given()
                .delete("/stores/" + id);

        deleteResponse.then().statusCode(200);
    }

    @Test
    public void testDeleteStoreInvalidId() {
        Response response = given()
                .delete("/stores/invalidId");

        response.then().statusCode(404);
    }
}
