package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;
    private HeaderComponent header;
    private final By backHomeButton = By.cssSelector("[data-test ='back-to-product']");
    private final By generatePDFButton = By.cssSelector("[data-test ='generate-pdf-order']");
    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }
    public HeaderComponent header() {
        return header;
    }
    public InventoryPage clickBackHomeButton(){
        driver.findElement(backHomeButton).click();
        return new InventoryPage(driver);
    }

    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }
}
