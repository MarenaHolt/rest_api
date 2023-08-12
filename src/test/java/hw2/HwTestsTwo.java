package hw2;

import hw2.models.CreateUserRequestModel;
import hw2.models.CreateUserResponseModel;
import hw2.models.SingleUserResponseModel;
import hw2.models.UserDataResponseModel;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static hw2.specs.LoginSpecHw.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HwTestsTwo {

    @BeforeAll
    static public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    @Tag("all")
    void testCheckFirstEmailInList() {
        UserDataResponseModel userDataResponseModel = step("Make request", () ->
                given(usersRequestSpecHw)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(listUsersResponseSpec)
                        .extract().as(UserDataResponseModel.class));

        step("Check response", () -> {
            assertEquals("michael.lawson@reqres.in", userDataResponseModel.getData().get(0).getEmail());
        });
    }

    @Test
    @Tag("all")
    void testCheckNameSingleUser() {
        SingleUserResponseModel singleUserResponseModel = step("Make request", () ->
                given(usersRequestSpecHw)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(singleUserResponseSpec)
                        .extract().as(SingleUserResponseModel.class));
        step("Check response", () -> {
            assertEquals("Janet", singleUserResponseModel.getData().getFirstName());
        });
    }

    @Test
    @Tag("all")
    void testCheckCreateUser() {
        CreateUserRequestModel createUserRequestModel = new CreateUserRequestModel();
        createUserRequestModel.setName("morpheus");
        createUserRequestModel.setJob("leader");

        CreateUserResponseModel createUserResponseModel = step("Make request", () ->
                given(usersRequestSpecHw)
                        .body(createUserRequestModel)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertEquals(createUserRequestModel.getName(), createUserResponseModel.getName());
            assertEquals(createUserRequestModel.getJob(), createUserResponseModel.getJob());
        });
    }

    @Test
    @Tag("all")
    void testCheckUpdateUser() {
        CreateUserRequestModel createUserRequestModel = new CreateUserRequestModel();
        createUserRequestModel.setName("morpheus");
        createUserRequestModel.setJob("zion resident");

        CreateUserResponseModel createUserResponseModel = step("Make request", () ->
                given(usersRequestSpecHw)
                        .body(createUserRequestModel)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertEquals(createUserRequestModel.getName(), createUserResponseModel.getName());
            assertEquals(createUserRequestModel.getJob(), createUserResponseModel.getJob());
        });
    }

    @Test
    @Tag("all")
    void testCheckDeleteUsers() {
        given(usersRequestSpecHw)
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteUsers204Spec);
    }
}
