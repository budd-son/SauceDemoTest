package com.nekhaichik.steps;

import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final LoginPage loginPage;
    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }
    public LoginPage openLoginPage() {
        return loginPage.openLoginPage();
    }
    public InventoryPage login(String username, String password) {
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLogin();
        return new InventoryPage(loginPage.driver);
    }
    public String getErrorMessage() {
        return loginPage.getError();
    }
}
