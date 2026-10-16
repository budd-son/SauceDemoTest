package com.nekhaichik.steps;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.CheckoutStepOnePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CartSteps {
    private final WebDriver driver;
    private final Logger log;
    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());
    }

    private CartPage page() {
        return new CartPage(driver);
    }

    public CartSteps removeItem(String itemName) {
        page().removeItem(itemName);
        log.info("Removed Item: {}", itemName);
        return this;
    }

    public boolean containsItem(String itemName) {
        return page().getCartItemNames().contains(itemName);
    }

    public boolean isCartEmpty() {
        return page().isCartEmpty();
    }

    public CheckoutStepOnePage proceedToCheckout() {
        page().clickCheckout();
        log.info("go to completed");
        return new CheckoutStepOnePage(driver);
    }

    public InventorySteps continueShopping() {
        page().clickContinueShopping();
        log.info("Back to shopping page");
        return new InventorySteps(driver);
    }
}