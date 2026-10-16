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
    public LoginPage enterUserName(String username){
        WebElement userNameInput = driver.findElement(usernameInput);
        userNameInput.clear();
        userNameInput.sendKeys(username);
        log.debug("Entered user name is: " + username);
        return this;
    }
    public LoginPage enterPassword(String password){
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
        log.debug("Entered password");
        return this;
    }

    public LoginPage clickLoginButton(){
        click(loginButton);
        log.debug("Clicked login button");
        return this;
    }
    public InventoryPage login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        waitForUrl("/inventory.html");
        log.debug("Logging");
        return new InventoryPage(driver);
    }


    public String getError() {
        WebElement errorElement = driver.findElement(errorMessage);
        log.debug("Error message: {}", errorElement.getText());
        return errorElement.getText();
    }



}
