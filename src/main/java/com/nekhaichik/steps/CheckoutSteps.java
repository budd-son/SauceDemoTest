package com.nekhaichik.steps;

import com.nekhaichik.pages.CheckoutCompletePage;
import com.nekhaichik.pages.CheckoutStepOnePage;
import com.nekhaichik.pages.CheckoutStepTwoPage;
import org.openqa.selenium.WebDriver;

public class CheckoutSteps {

    private CheckoutStepOnePage stepOne;
    private final WebDriver driver;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
        this.stepOne = new CheckoutStepOnePage(driver);
    }

    public CheckoutSteps fillCustomerInfo(String firstName, String lastName, String postalCode) {
        stepOne.typeFirstName(firstName);
        stepOne.typeLastName(lastName);
        stepOne.typePostalCode(postalCode);
        return this;
    }

    public CheckoutStepTwoPage continueToOverview() {
        stepOne.clickContinue();
        return new CheckoutStepTwoPage(driver);
    }

    public CheckoutStepOnePage continueExpectingError() {
        stepOne.clickContinue();
        return stepOne;
    }

    public String getError() {
        return stepOne.getError();
    }

    public CheckoutCompletePage finish() {
        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        stepTwo.clickFinish();
        return new CheckoutCompletePage(driver);
    }
}