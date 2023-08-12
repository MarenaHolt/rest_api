package com.selenoid;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    /*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response { total: 20, used: 0, queued: 0, pending: 0, browsers: ...
    3. Check total is 20
     */
    @Test
    void checkTotalMini() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithLogs() {
        given()
                .log().all() //залогировали запрос
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all() //залогировали ответ
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLogs() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithStatus() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithChrome() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));
    }
}
