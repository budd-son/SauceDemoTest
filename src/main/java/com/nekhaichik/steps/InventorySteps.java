package com.nekhaichik.steps;

import com.nekhaichik.pages.InventoryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventorySteps {
    private final WebDriver driver;
    protected final Logger log;
    public InventorySteps(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());

    }

    private InventoryPage page() {
        return new InventoryPage(driver);
    }

    public boolean isOpened() {
        return page().isOpened();
    }

    public InventorySteps addToCart(String itemName) {
        page().clickItemButton(itemName);
        log.info("Add to cart: {}", itemName);
        return this;
    }

    public InventorySteps removeFromCart(String itemName) {
        page().clickItemButton(itemName);
        log.info("Remove from cart: {}", itemName);
        return this;
    }

    public InventorySteps sortBy(String option) {
        page().selectSortOption(option);
        log.info("Sort by: {}", option);
        return this;
    }

    public CartSteps openCart() {
        page().header().openCart();
        return new CartSteps(driver);
    }

    public List<Double> getItemPrices() {
        return page().getItemPrices();
    }

    public int getCartItemCount() {
        return page().header().getCartItemCount();
    }

    public String getItemButtonText(String itemName) {
        return page().getItemButtonText(itemName);
    }

    public boolean isCartBadgeDisplayed() {
        return page().header().isCartBadgeDisplayed();
    }
}
