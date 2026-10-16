package com.nekhaichik.steps;

import com.nekhaichik.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final WebDriver driver;
    protected final Logger log;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());
    }

    private LoginPage page() {
        return new LoginPage(driver);
    }

    public LoginSteps openLoginPage() {
        page().openLoginPage();
        return this;
    }

    public InventorySteps loginAs(String username, String password) {
        LoginPage page = page();
        page.typeUsername(username);
        page.typePassword(password);
        page.clickLogin();
        log.info("Logged in as " + username);
        return new InventorySteps(driver);
    }

    public String getErrorMessage() {
        log.error("getErrorMessage");
        return page().getError();
    }
}