package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOnePage  extends CheckoutStepTwoPage {
    private static final Logger log = LogManager.getLogger(CheckoutStepOnePage.class);
    private final HeaderComponent header;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By buttonContinue = By.id("continue");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
    private By errorMessage = By.xpath("//*[contains(text(),'Error:')]");
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        log.info("Checkout Step One Page");
    }
    public HeaderComponent header() {
        return header;
    }

    public CheckoutStepOnePage inputFirstName(String firstName) {
        WebElement input = driver.findElement(this.firstName);
        input.clear();
        input.sendKeys(firstName);
        log.debug("Input First Name : {}", firstName);
        return this;
    }
    public CheckoutStepOnePage inputLastName(String lastName) {
        WebElement input = driver.findElement(this.lastName);
        input.clear();
        input.sendKeys(lastName);
        log.debug("Input Last Name : {}", lastName);
        return this;
    }
    public CheckoutStepOnePage inputPostalCode(String postalCode) {
        WebElement input = driver.findElement(this.postalCode);
        input.clear();
        input.sendKeys(postalCode);
        log.debug("Input Postal Code : {}", postalCode);
        return this;
    }

    public CheckoutStepTwoPage clickContinue() {
        driver.findElement(buttonContinue).click();
        wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));

        log.debug("Click Continue");
        return new CheckoutStepTwoPage(driver);
    }
    public CheckoutStepOnePage clickContinueExpectingError() {
        driver.findElement(buttonContinue).click();
        log.debug("Click Continue with some empty field");
        return this;
    }
    public String getError() {
        WebElement errorElement = driver.findElement(errorMessage);
        log.debug("Error Message : {}", errorElement.getText());
        return errorElement.getText();
    }
    public CartPage clickCancel() {
        driver.findElement(cancelButton).click();
        wait.until(ExpectedConditions.urlContains("/cart.html"));

        log.debug("Click Cancel comeback to cart");
        return new CartPage(driver);
    }

}
