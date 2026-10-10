package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;
    private By productSort = By.xpath("//*[@class='product_sort_container']");
    private final HeaderComponent header;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }
    public HeaderComponent header() {
        return header;
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

    public InventoryPage addToCart(String itemName) {
        driver.findElement(itemButton(itemName)).click();
        return this;
    }

    public InventoryPage removeFromCart(String itemName) {
        driver.findElement(itemButton(itemName)).click();
        return this;
    }

    public String getItemButtonText(String itemName) {
        return driver.findElement(itemButton(itemName)).getText();
    }

}
