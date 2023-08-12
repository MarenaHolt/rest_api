package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginSpec {

    //спецификация запроса
    public static RequestSpecification loginRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
    //.filter(new AllureRestAssured()) //подключение аллюр
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api");

    //спецификация ответа
    public static ResponseSpecification loginResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(200)
                    .expectBody(matchesJsonSchemaInClasspath("schemas/success-login-response-schema.json"))
                    //.expectBody("token", notNullValue())
                    .build();

    public static ResponseSpecification missingPassword400Spec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(BODY)
                    .expectStatusCode(400)
                    .expectBody("error", notNullValue()) //todo move to schema validation
                    .build();
}
