package com.demoqa.api;

import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BooksApi {
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);
    }

    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList ) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }



    public void deleteBook(LoginResponseModel loginResponse, DeleteBookModel deleteBook) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .statusCode(204);
    }
}