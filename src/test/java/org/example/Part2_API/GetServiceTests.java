package org.example.Part2_API;
// Converted Tests for Getting a Single Service by ID
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetServiceTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetServiceByIdSuccess() {
        String servicePayload = "{" +
                ""name": "Repair"}";

        Response createResponse = given()
                .contentType("application/json")
                .body(servicePayload)
                .post("/services");

        String id = createResponse.then().extract().path("id");

        Response getResponse = given()
                .get("/services/" + id);

        getResponse.then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Repair"));
    }

    @Test
    public void testGetServiceByIdNotFound() {
        Response response = given()
                .get("/services/invalidId");

        response.then().statusCode(404);
    }
}
