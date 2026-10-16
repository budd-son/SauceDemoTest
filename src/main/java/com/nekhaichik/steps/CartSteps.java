package com.nekhaichik.steps;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.CheckoutStepOnePage;
import com.nekhaichik.pages.InventoryPage;
import org.openqa.selenium.WebDriver;

public class CartSteps {

    private final CartPage cartPage;

    public CartSteps(WebDriver driver) {
        this.cartPage = new CartPage(driver);
    }

    public CartSteps removeItem(String itemName) {
        cartPage.removeItem(itemName);
        return this;
    }

    public boolean containsItem(String itemName) {
        return cartPage.getCartItemNames().contains(itemName);
    }

    public CheckoutStepOnePage proceedToCheckout() {
        cartPage.clickCheckout();
        return new CheckoutStepOnePage(cartPage.driver);
    }

    public InventoryPage continueShopping() {
        cartPage.clickContinueShopping();
        return new InventoryPage(cartPage.driver);
    }
}