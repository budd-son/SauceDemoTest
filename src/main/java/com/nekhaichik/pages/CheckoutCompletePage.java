package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }
    private final By backHomeButton = By.cssSelector("[data-test ='back-to-product']");
    private final By generatePDFButton = By.cssSelector("[data-test ='generate-pdf-order']");
    public InventoryPage clickBackHomeButton(){
        driver.findElement(backHomeButton).click();
        return new InventoryPage(driver);
    }
}
