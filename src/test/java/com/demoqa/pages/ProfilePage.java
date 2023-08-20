package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private SelenideElement userNameValue = $("#userName-value"),
            noRows = $("[class=rt-noData]");

    public ProfilePage checkUsername(String userName) {
        userNameValue.shouldHave(text(userName));
        return this;
    }

    public ProfilePage noRowsCheck() {
        noRows.should(exist);
        return this;
    }
}
