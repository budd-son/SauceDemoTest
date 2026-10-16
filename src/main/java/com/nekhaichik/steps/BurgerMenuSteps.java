package com.nekhaichik.steps;

import com.nekhaichik.pages.InventoryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BurgerMenuSteps {
    private final WebDriver driver;
    private final Logger log;

    public BurgerMenuSteps(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());
    }

    private InventoryPage page() {
        return new InventoryPage(driver);
    }

    public BurgerMenuSteps openAbout() {
        page().header().openBurgerMenu().openAbout();
        log.info("Open about page");
        return this;
    }

    public BurgerMenuSteps resetAppState() {
        page().header().openBurgerMenu().resetAppState();
        log.info("Reset app state");
        return this;
    }

    public BurgerMenuSteps close() {
        page().header().closeBurgerMenu();
        log.info("Close burger menu");
        return this;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}