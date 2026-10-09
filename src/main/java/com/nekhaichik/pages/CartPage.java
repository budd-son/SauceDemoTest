package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private final WebDriver driver;

    private final By cartItems     = By.cssSelector(".cart_item");
    private final By cartItemNames = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getCartItemNames() {
        return driver.findElements(cartItemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }

    public boolean containsCartItem(String itemName) {
        return getCartItemNames().contains(itemName);
    }

    private By itemButton(String itemName) {
        return By.xpath(
                "//div[@class='cart_list'][.//div[text()='" + itemName + "']]//button"
        );
    }

    public void removeItem(String itemName) {
        driver.findElement(itemButton(itemName)).click();
    }
}