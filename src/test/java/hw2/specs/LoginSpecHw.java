package hw2.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginSpecHw {

    //спецификация запроса
    public static RequestSpecification usersRequestSpecHw = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api");

    //спецификация ответа
    public static ResponseSpecification listUsersResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(200)
                    .expectBody(matchesJsonSchemaInClasspath("schemas/list-users-schema.json"))
                    .build();

    public static ResponseSpecification singleUserResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(200)
                    .expectBody(matchesJsonSchemaInClasspath("schemas/single-user-response-schema.json"))
                    .build();

    public static ResponseSpecification createUserResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(201)
                    .expectBody(matchesJsonSchemaInClasspath("schemas/create-user-response-schema.json"))
                    .build();

    public static ResponseSpecification updateUserResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(200)
                    .expectBody(matchesJsonSchemaInClasspath("schemas/update-user-response-schema.json"))
                    .build();



    public static ResponseSpecification deleteUsers204Spec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(204)
                    .build();
}
