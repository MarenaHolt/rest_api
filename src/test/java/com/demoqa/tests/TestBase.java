package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.CredentialsModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();
    IsbnModel isbnModel = new IsbnModel();
    ProfilePage profilePage = new ProfilePage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }

    public AddBooksListModel createBookList(LoginResponseModel loginResponse, String setIsbn) {
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(setIsbn);
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);
        return booksList;
    }

    public void openProfilePage(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
    }
}
