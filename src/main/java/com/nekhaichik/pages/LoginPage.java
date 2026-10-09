package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath("//*[contains(text(),'Epic sadface')]");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage openLoginPage() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }
    public LoginPage enterUserName(String username){
        WebElement userNameInput = driver.findElement(usernameInput);
        userNameInput.clear();
        userNameInput.sendKeys(username);
        return this;
    }
    public LoginPage enterPassword(String password){
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
        return this;
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    public InventoryPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        return new InventoryPage(driver);
    }
    public String getError() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.getText();
    }





}
