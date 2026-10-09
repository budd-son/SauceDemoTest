package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;
    private By inventoryContainer = By.id("inventory_container");
    private By productSort = By.xpath("//*[@class='product_sort_container']");
    private By shoppingCart = By.cssSelector(".shopping_cart_link");
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    public InventoryPage open() {
        driver.get("https://www.saucedemo.com/inventory.html");
        return this;
    }
    public List<Double> getItemPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".inventory_item_price"));
        return priceElements.stream()
                .map(element -> element.getText())
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public InventoryPage selectSortOption(String text){
        WebElement sortOption = driver.findElement(productSort);
        new Select(sortOption).selectByVisibleText(text);
        return this;
    }

    private By itemButton(String itemName) {
        return By.xpath(
                "//div[@class='inventory_item'][.//div[text()='" + itemName + "']]//button"
        );
    }

    public InventoryPage addToCartButton(String itemName) {
        driver.findElement(itemButton(itemName)).click();
        return this;
    }
    public String getCartBadgeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement badge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(shoppingCart)
        );
        return badge.getText();
    }
    public void clickCartButton() {
        driver.findElement(shoppingCart).click();
    }
}
