package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BaseLoggedInPage {
    private final By cartItems = By.cssSelector(".cart_item");
    private final By cartItemNames = By.cssSelector(".inventory_item_name");
    private final By checkout = By.xpath("//button[contains(text(),'Checkout')]");
    private final By continueShoppingBtn = By.xpath("//button[@data-test='continue-shopping']");
    private final String itemButton =
            "//div[@class='cart_item'][.//div[normalize-space()=%s]]//button";

    public CartPage(WebDriver driver) {
        super(driver);
        waitForUrl("/cart.html");
    }

    public List<String> getCartItemNames() {
        return driver.findElements(cartItemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public CartPage removeItem(String itemName) {
        click(byText(itemButton, itemName));
        log.debug("Removed item {}", itemName);
        return this;
    }

    public boolean isCartEmpty() {
        return wait.until(ExpectedConditions.numberOfElementsToBe(cartItems, 0)).isEmpty();
    }

    public void clickCheckout() {
        click(checkout);
    }

    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }
}