package hw;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class HwTests {

    @BeforeAll
    static public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void testCheckFirstEmailInList() {
        given()
                .log().all() //залогировали запрос
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data[0].email", is("michael.lawson@reqres.in"));
    }

    @Test
    void testCheckNameSingleUser() {
        given()
                .log().all()
                .when()
                .get("/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.first_name", is("Janet"));
    }

    @Test
    void testCheckCreateUser() {
        String data = "{\"name\": \"morpheus\",\n" +
                "\"job\": \"leader\"}";
        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", is(notNullValue()));
    }

    @Test
    void testCheckUpdateUser() {
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("updatedAt", is(notNullValue()));
    }

    @Test
    void testCheckDeleteUsers() {
        given()
                .log().all()
                .when()
                .delete("/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }
}
