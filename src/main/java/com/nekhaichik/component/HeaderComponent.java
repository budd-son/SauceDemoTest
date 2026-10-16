package com.nekhaichik.component;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HeaderComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Logger log = LogManager.getLogger(HeaderComponent.class);

    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By closeButton = By.cssSelector(".bm-cross-button");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public String getCartBadgeText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
        log.debug("Cart badge text: {}", text);
        return text;
    }

    public int getCartItemCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            return 0;
        }
        int count = Integer.parseInt(badges.get(0).getText());
        log.debug("Cart item count: {}", count);
        return count;
    }

    public boolean isCartBadgeDisplayed() {
        List<WebElement> badges = driver.findElements(cartBadge);
        return !badges.isEmpty() && badges.get(0).isDisplayed();
    }

    public CartPage openCart() {
        click(cartLink);
        log.debug("Open cart page");
        return new CartPage(driver);
    }

    public HeaderComponent openBurgerMenu() {
        click(burgerMenuButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        log.debug("Burger menu opened");
        return this;
    }

    public HeaderComponent closeBurgerMenu() {
        click(closeButton);
        log.debug("Burger menu closed");
        return this;
    }

    public LoginPage logout() {
        click(logoutLink);
        log.debug("Logout");
        return new LoginPage(driver);
    }

    public void openAbout() {
        click(aboutLink);
        wait.until(ExpectedConditions.urlContains("saucelabs.com"));
        log.debug("About page opened");
    }

    public HeaderComponent resetAppState() {
        click(resetLink);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        log.debug("App state reset");
        return this;
    }
}