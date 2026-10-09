package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    WebDriver driver;
    private final By totalPrice = By.cssSelector("total-price");
    private final By taxPrice = By.cssSelector("tax-label");
    private final By subTotalPrice = By.cssSelector("subtotal-label");
    private final By finish = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }
    public CheckoutStepTwoPage openCheckoutStepTwoPage()
    {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        return this;
    }
    public Double getSubTotalSum(){
        return Double.parseDouble(
                driver.findElement(subTotalPrice)
                        .getText()
                        .replaceAll("\\$",""));
    }
    public Double getTotalSum(){
        return Double.parseDouble(driver.findElement(taxPrice)
                .getText()
                .replaceAll("\\$",""));
    }
    public Double getTaxSum(){
        return Double.parseDouble(driver.findElement(taxPrice)
                .getText()
                .replaceAll("\\$",""));
    }
    public CheckoutCompletePage finish(){
        driver.findElement(finish).click();
        return new CheckoutCompletePage(driver);
    }
}
