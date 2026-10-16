package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends BaseLoggedInPage {
    private final By totalLabel = By.cssSelector(".summary_total_label");
    private final By taxLabel = By.cssSelector(".summary_tax_label");
    private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By finish = By.id("finish");
    private final By buttonBack = By.id("cancel");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
    }

    public void clickFinish() {
        click(finish);
    }

    public void clickCancel() {
        click(buttonBack);
    }

    public double getSubTotal() {
        return parseMoney(getText(subtotalLabel));
    }

    public double getTax(){
        return parseMoney(getText(taxLabel));
    }

    public double getTotal(){
        return parseMoney(getText(totalLabel));

    }

    private double parseMoney(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}