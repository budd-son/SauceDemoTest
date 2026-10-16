package com.nekhaichik.component;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;

public class HeaderComponent {
    private final WebDriver driver;
    Logger log ;
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By closeButton = By.cssSelector(".bm-cross-button");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");
    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        this.log = LogManager.getLogger(this.getClass());
    }

    public String getCartBadgeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement badge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        );
        log.debug("Get badge text {}", badge.getText());
        return badge.getText();
    }
    public int getCartItemCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        log.debug("Get cart item count {}", driver.findElements(cartBadge).size());
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public boolean isCartBadgeDisplayed() {
        log.debug("Check is cart badge");
        List<WebElement> badges = driver.findElements(cartBadge);
        return !badges.isEmpty() && badges.getFirst().isDisplayed();
    }
    //isDisplayed() выбрасывал NoSuchElement поэтому просто проверяла отображение в DOM

    public CartPage openCart() {
        driver.findElement(cartLink).click();
        log.debug("Open cart page");
        return new CartPage(driver);
    }

    public HeaderComponent openBurgerMenu() {
        driver.findElement(burgerMenuButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        log.debug("Open burger menu page");
        return this;
    }

    public HeaderComponent closeBurgerMenu() {
        driver.findElement(closeButton).click();
        log.debug("Close burger menu page");
        return this;
    }

    public LoginPage logout() {
        driver.findElement(logoutLink).click();
        log.debug("Logout page");
        return new LoginPage(driver);
    }

    public void openAbout() {
        driver.findElement(aboutLink).click();
        log.debug("Open about page");
    }

    public HeaderComponent resetAppState() {
        driver.findElement(resetLink).click();
        log.debug("Reset state page");
        return this;
    }
}