package com.nekhaichik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage  extends BaseLoggedInPage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By buttonContinue = By.id("continue");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
    private final By errorMessage = By.xpath("//*[contains(text(),'Error:')]");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
    }

    public CheckoutStepOnePage typeFirstName(String stringFirstName) {
        fillElement(firstName, stringFirstName);
        return this;
    }

    public CheckoutStepOnePage typeLastName(String stringLastName) {
        fillElement(lastName, stringLastName);
        return this;
    }

    public CheckoutStepOnePage typePostalCode(String stringPostalCode) {
        fillElement(postalCode, stringPostalCode);
        return this;
    }
    public void clickContinue() {
        click(buttonContinue);
        log.debug("Click Continue");
    }

    public void clickCancel() {
        click(cancelButton);
        log.debug("Click Cancel comeback to cart");
    }
    public String getError() {
        return getText(errorMessage);
    }
}
