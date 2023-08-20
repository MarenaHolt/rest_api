package com.demoqa.tests;

import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import com.demoqa.helpers.MainTestMethods;
import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.tests.TestData.credentials;

public class ProfileBooksListTests extends TestBase {

    MainTestMethods mainTestMethods = new MainTestMethods();
    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();
    ProfilePage profilePage = new ProfilePage();

    /*
    этот тест не трогаю, остается для примера
     */
    @Test
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
        $("[id='see-book-Git Pocket Guide']").shouldBe(visible);
    }

    @Test
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);
        AddBooksListModel addBooksListModel = mainTestMethods.createBookList(loginResponse, "9781449325862");
        booksApi.addBook(loginResponse, mainTestMethods.createBookList(loginResponse, addBooksListModel.getCollectionOfIsbns().get(0).getIsbn()));

        booksApi.deleteBook(loginResponse, new DeleteBookModel(addBooksListModel.getCollectionOfIsbns().get(0).getIsbn(), loginResponse.getUserId()));

        mainTestMethods.openProfilePage(loginResponse);

        profilePage.checkUsername(loginResponse.getUserName());
        profilePage.noRowsCheck();
    }
}
