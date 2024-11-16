package org.example.Part2_API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BestBuyHelpers {

    private static final String BASE_URL = "http://localhost:3030";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response create(String endpointName, String sample) {
        return RestAssured.given()
                .contentType("application/json")
                .body(sample)
                .post("/" + endpointName);
    }

    public static Response get(String endpointName, int limit, int skip) {
        return RestAssured.given()
                .get("/" + endpointName + "?$limit=" + limit + "&$skip=" + skip);
    }

    public static Response delete(String endpointName, String id) {
        return RestAssured.given()
                .delete("/" + endpointName + "/" + id);
    }

    public static Response getById(String endpointName, String id) {
        return RestAssured.given()
                .get("/" + endpointName + "/" + id);
    }

    public static Response update(String endpointName, String id, String sample) {
        return RestAssured.given()
                .contentType("application/json")
                .body(sample)
                .patch("/" + endpointName + "/" + id);
    }

    public static Response getVersion() {
        return RestAssured.get("/version");
    }

    public static Response getHealthCheck() {
        return RestAssured.get("/healthcheck");
    }
}
