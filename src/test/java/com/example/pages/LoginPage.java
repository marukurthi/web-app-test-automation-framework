package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isInventoryVisible() {
        return !driver.findElements(inventoryContainer).isEmpty();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }

    public boolean isLoginButtonVisible() {
        return !driver.findElements(loginButton).isEmpty();
    }

    public WebElement usernameField() {
        return driver.findElement(username);
    }

    public WebElement passwordField() {
        return driver.findElement(password);
    }
}
