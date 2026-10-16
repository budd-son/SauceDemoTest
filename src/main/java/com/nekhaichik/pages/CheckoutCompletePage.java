package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BaseLoggedInPage {
    private final By backHomeButton = By.cssSelector("[data-test ='back-to-product']");
    private final By generatePDFButton = By.cssSelector("[data-test ='generate-pdf-order']");
    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        log.info("Checkout complete page opened");
    }
    public InventoryPage clickBackHomeButton(){
        click(backHomeButton);
        log.debug("Click Back Home button to cart");
        return new InventoryPage(driver);
    }

    public String getCompleteHeader() {
        String headerText = driver.findElement(completeHeader).getText();
        log.debug("Get header text {}", headerText);
        return headerText;
    }
}
