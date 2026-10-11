package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Valid user can log in and see the products page")
    public void validLoginShowsProducts() {
        LoginPage page = new LoginPage(driver);
        page.login("standard_user", "secret_sauce");
        Assert.assertTrue(page.isInventoryVisible(),
                "Products page should be visible after a valid login.");
    }

    @Test(description = "Invalid password displays a validation message")
    public void invalidPasswordShowsError() {
        LoginPage page = new LoginPage(driver);
        page.login("standard_user", "wrong_password");
        Assert.assertTrue(page.getErrorMessage().contains("Username and password do not match"),
                "An error message should be shown for an invalid password.");
    }

    @Test(description = "Logout returns the user to the login page")
    public void logoutReturnsToLogin() {
        LoginPage page = new LoginPage(driver);
        page.login("standard_user", "secret_sauce");
        Assert.assertTrue(page.isInventoryVisible(), "Login should succeed before logout.");
        page.logout();
        Assert.assertTrue(page.isLoginButtonVisible(), "Login button should be visible after logout.");
    }
}
