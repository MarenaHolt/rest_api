package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import com.demoqa.models.CredentialsModel;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}
