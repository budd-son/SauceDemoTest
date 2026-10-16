package com.nekhaichik.steps;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.CheckoutStepTwoPage;
import com.nekhaichik.pages.InventoryPage;

public class InventorySteps {
    private final InventoryPage inventoryPage;
    public InventorySteps(InventoryPage inventoryPage) {
        this.inventoryPage = inventoryPage;
    }
    public InventorySteps addToCart(String itemName) {
        inventoryPage.clickItemButton(itemName);
        return this;
    }

    public InventorySteps removeFromCart(String itemName) {
        inventoryPage.clickItemButton(itemName);
        return this;
    }
    public InventorySteps sortBy(String option) {
        inventoryPage.selectSortOption(option);
        return this;
    }

    public CartPage openCart() {
        inventoryPage.header().openCart();
        return new CartPage(inventoryPage.driver);
    }

    public boolean isButtonText(String itemName, String expected) {
        return inventoryPage.getItemButtonText(itemName).equals(expected);
    }

}
