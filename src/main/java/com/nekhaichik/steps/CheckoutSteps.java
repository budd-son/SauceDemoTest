package com.nekhaichik.steps;

import com.nekhaichik.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CheckoutSteps {
    private final WebDriver driver;
    protected final Logger log;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());

    }

    private CheckoutStepOnePage stepOne() {
        return new CheckoutStepOnePage(driver);
    }

    public CheckoutSteps fillCustomerInfo(String firstName, String lastName, String postalCode) {
        CheckoutStepOnePage page = stepOne();
        page.typeFirstName(firstName)
                .typeLastName(lastName)
                .typePostalCode(postalCode);
        log.info("Filling customer information: {}" + firstName + " " + lastName + " " + postalCode);
        return this;
    }

    public CheckoutStepTwoPage continueToOverview() {
        stepOne().clickContinue();
        return new CheckoutStepTwoPage(driver);
    }

    public CheckoutSteps continueExpectingError() {
        stepOne().clickContinue();
        return this;
    }

    public String getError() {
        return stepOne().getError();
    }

    public CheckoutCompletePage finish() {
        new CheckoutStepTwoPage(driver).clickFinish();
        log.info("Checkout completed");
        return new CheckoutCompletePage(driver);
    }
}