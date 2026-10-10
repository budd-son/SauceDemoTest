package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    WebDriver driver;
    private By burgerMenu = By.id("react-burger-menu-btn");

    private final By totalLabel    = By.cssSelector(".summary_total_label");
    private final By taxLabel      = By.cssSelector(".summary_tax_label");
    private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By finish = By.id("finish");
    private final By buttonBack = By.id("cancel");
    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public Double getSubTotalSum(){
        return Double.parseDouble(
                driver.findElement(subtotalLabel)
                        .getText()
                        .replaceAll("[^0-9.]", ""));
    }
    public Double getTotalSum(){
        return Double.parseDouble(driver.findElement(totalLabel)
                .getText()
                .replaceAll("[^0-9.]", ""));
    }
    public Double getTaxSum(){
        return Double.parseDouble(driver.findElement(taxLabel)
                .getText()
                .replaceAll("[^0-9.]", ""));
    }
    public CheckoutCompletePage finish(){
        driver.findElement(finish).click();
        return new CheckoutCompletePage(driver);
    }
    public CartPage cancelClick() {
        driver.findElement(buttonBack).click();
        return new CartPage(driver);
    }
}
