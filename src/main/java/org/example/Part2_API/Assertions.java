package org.example.Part2_API;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;

public class Assertions {

    public static void assertCreateSuccessStatusCode(Response response) {
        assertResponseStatusCode(response, 201);
    }

    public static void assertSuccessStatusCode(Response response) {
        assertResponseStatusCode(response, 200);
    }

    public static void assertBadRequestStatusCode(Response response) {
        assertResponseStatusCode(response, 400);
    }

    public static void assertNotFoundErrorStatusCode(Response response) {
        assertResponseStatusCode(response, 404);
    }

    public static void assertInternalServerErrorStatusCode(Response response) {
        assertResponseStatusCode(response, 500);
    }

    public static void assertResponseBody(Response response, String expectedResponse) {
        assertThat(response.getBody().asString(), containsString(expectedResponse));
    }

    private static void assertResponseStatusCode(Response response, int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
}
