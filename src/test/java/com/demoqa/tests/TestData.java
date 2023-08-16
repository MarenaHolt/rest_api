package com.demoqa.tests;

import com.demoqa.models.CredentialsModel;

public class TestData {

    private static String login = "test123456",
            password = "Test123456@";

    public static CredentialsModel credentials = new CredentialsModel(login, password);


}
