package com.nekhaichik.component;

import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent {
    private final WebDriver driver;

    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By closeButton = By.cssSelector(".bm-cross-button");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");
    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartBadgeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement badge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        );
        return badge.getText();
    }
    public int getCartItemCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public CartPage openCart() {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }

    public HeaderComponent openBurgerMenu() {
        driver.findElement(burgerMenuButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        return this;
    }

    public HeaderComponent closeBurgerMenu() {
        driver.findElement(closeButton).click();
        return this;
    }

    public LoginPage logout() {
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public void openAbout() {
        driver.findElement(aboutLink).click();
    }

    public HeaderComponent resetAppState() {
        driver.findElement(resetLink).click();
        return this;
    }
}