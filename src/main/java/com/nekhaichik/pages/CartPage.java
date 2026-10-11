package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private final WebDriver driver;
    private final HeaderComponent header;

    private final By cartItems     = By.cssSelector(".cart_item");
    private final By cartItemNames = By.cssSelector(".inventory_item_name");
    private final By checkout = By.xpath("//button[contains(text(),'Checkout')]");
    private By continueShoppingBtn = By.xpath("//button[@data-test='continue-shopping']");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }
    public HeaderComponent header() {
        return header;
    }

    public List<String> getCartItemNames() {
        return driver.findElements(cartItemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean containsCartItem(String itemName) {
        return getCartItemNames().contains(itemName);
    }

    private By itemButton(String itemName) {
        return By.xpath(
                "//div[@class='cart_list'][.//div[text()='" + itemName + "']]//button"
        );
    }

    public CartPage removeItem(String itemName) {
        driver.findElement(itemButton(itemName)).click();
        return this;
    }
    public CheckoutStepOnePage openCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(checkout)).click();
        return new CheckoutStepOnePage(driver);
    }
    public InventoryPage continueShopping() {
        driver.findElement(continueShoppingBtn).click();
        return new InventoryPage(driver);
    }
    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }

}