package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private HeaderComponent header;
    private final By backHomeButton = By.cssSelector("[data-test ='back-to-product']");
    private final By generatePDFButton = By.cssSelector("[data-test ='generate-pdf-order']");
    private final By completeHeader = By.cssSelector(".complete-header");


    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        log.info("Checkout complete page opened");
    }
    public HeaderComponent header() {
        return header;
    }
    public InventoryPage clickBackHomeButton(){
        driver.findElement(backHomeButton).click();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        log.debug("Click Back Home button to cart");
        return new InventoryPage(driver);
    }

    public String getCompleteHeader() {
        String headerText = driver.findElement(completeHeader).getText();
        log.debug("Get header text {}", headerText);
        return headerText;
    }
}
