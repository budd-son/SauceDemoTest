package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;
    private By inventoryContainer = By.id("inventory_container");
    private By productSort = By.xpath("//*[@class='product_sort_container']");

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

}
