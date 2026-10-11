package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormValidationTests extends BaseTest {

    @Test(description = "Empty credentials display a validation message")
    public void emptyCredentialsShowError() {
        LoginPage page = new LoginPage(driver);
        page.clickLogin();
        Assert.assertTrue(page.getErrorMessage().toLowerCase().contains("username is required"),
                "A username-required message should be displayed.");
    }
}
