package org.example.Part2_API;
// Converted Tests for Getting All Services
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetServicesTests {

    private static final String BASE_URL = "http://localhost:3030";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetServicesSuccess() {
        Response response = given()
                .get("/services");

        response.then().statusCode(200);
    }

    @Test
    public void testGetServicesTotalCount() {
        Response response = given()
                .get("/services");

        response.then().statusCode(200)
                .body("total", instanceOf(Integer.class));
    }
}
