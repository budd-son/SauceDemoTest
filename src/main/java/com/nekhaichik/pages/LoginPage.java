package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//*[contains(text(),'Epic sadface')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPage() {
        driver.get(BASE_URL);
        log.info("Opening login page");
        return this;
    }

    public LoginPage typeUsername(String username) {
        fillElement(usernameInput, username);
        return this;
    }

    public LoginPage typePassword(String password) {
        fillElement(passwordInput, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public String getError() {
        return getText(errorMessage);
    }
}
