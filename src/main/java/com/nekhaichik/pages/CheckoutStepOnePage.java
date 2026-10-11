package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOnePage {
    private final WebDriver driver;
    private final HeaderComponent header;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By buttonContinue = By.id("continue");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
    private By errorMessage = By.xpath("//*[contains(text(),'Error:')]");
    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }
    public HeaderComponent header() {
        return header;
    }

    public CheckoutStepOnePage inputFirstName(String firstName) {
        WebElement input = driver.findElement(this.firstName);
        input.clear();
        input.sendKeys(firstName);
        return this;
    }
    public CheckoutStepOnePage inputLastName(String lastName) {
        WebElement input = driver.findElement(this.lastName);
        input.clear();
        input.sendKeys(lastName);
        return this;
    }
    public CheckoutStepOnePage inputPostalCode(String postalCode) {
        WebElement input = driver.findElement(this.postalCode);
        input.clear();
        input.sendKeys(postalCode);
        return this;
    }
    public CheckoutStepTwoPage clickContinue() {
        driver.findElement(buttonContinue).click();
        return new CheckoutStepTwoPage(driver);
    }
    public CheckoutStepOnePage clickContinueExpectingError() {
        driver.findElement(buttonContinue).click();
        return this;
    }
    public String getError() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.getText();
    }
    public CartPage clickCancel() {
        driver.findElement(cancelButton).click();
        return new CartPage(driver);
    }

}
