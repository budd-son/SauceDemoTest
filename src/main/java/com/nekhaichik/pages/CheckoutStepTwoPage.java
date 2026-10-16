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
        log.info("Checkout Step Two Page  opened");
    }

    public Double getSubTotalSum(){
        Double result =Double.parseDouble(
                driver.findElement(subtotalLabel)
                        .getText()
                        .replaceAll("[^0-9.]", ""));
        log.debug("Subtotal Sum is: {}", result);

        return result;

    }
    public Double getTotalSum(){
        Double result =Double.parseDouble(driver.findElement(totalLabel)
                .getText()
                .replaceAll("[^0-9.]", ""));
        log.debug("Total Sum is: {}", result);
        return result;
    }
    public Double getTaxSum(){
        Double result =Double.parseDouble(driver.findElement(taxLabel)
                .getText()
                .replaceAll("[^0-9.]", ""));
        log.debug("Tax Sum is: {}", result);

        return result;
    }
    public CheckoutCompletePage finish(){
        driver.findElement(finish).click();
        wait.until(ExpectedConditions.urlContains("/checkout-complete.html"));

        log.debug("Click finish  go to complete page");
        return new CheckoutCompletePage(driver);
    }
    public CartPage cancelClick() {
        driver.findElement(buttonBack).click();
        wait.until(ExpectedConditions.urlContains("/cart.html"));

        log.debug("Click Cancel comeback to cart");
        return new CartPage(driver);
    }
}
