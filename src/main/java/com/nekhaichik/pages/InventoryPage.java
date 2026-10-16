package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BaseLoggedInPage {
    private final By productSort = By.xpath("//*[@class='product_sort_container']");
    private  final By itemPrices = By.cssSelector(".inventory_item_price");
    private  final String itemButton =
            "//div[@class='inventory_item'][.//div[normalize-space()=%s]]//button";
    public InventoryPage(WebDriver driver) {
        super(driver);
        waitForUrl("/inventory.html");
    }

    public List<Double> getItemPrices() {
        log.debug("Get item prices");
        return driver.findElements(itemPrices).stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public InventoryPage selectSortOption(String text){
        WebElement sortOption = driver.findElement(productSort);
        new Select(sortOption).selectByVisibleText(text);
        log.debug("In InventoryPage selectSortOption");
        return this;
    }

    public InventoryPage clickItemButton(String itemName) {
        log.debug("Click item button: {}", itemName);
        click(byText(itemButton, itemName));
        return this;
    }

    public String getItemButtonText(String itemName) {
        return getText(byText(itemButton, itemName));
    }
    public boolean isOpened() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }


}
