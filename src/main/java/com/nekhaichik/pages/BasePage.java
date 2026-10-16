package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public abstract class BasePage {
    protected final Logger log;
    public final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.log = LogManager.getLogger(this.getClass());
    }

    protected void click(By locator) {
        log.info("Click: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void fillElement(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        log.debug("Text of {}: '{}'", locator, text);
        return text;
    }

    protected void waitForUrl(String s) {
        log.debug("Wait for URL contains: {}", s);
        wait.until(ExpectedConditions.urlContains(s));
    }
    protected By byText(String xpathTemplate, String value) {
        return By.xpath(String.format(xpathTemplate, xpathLiteral(value)));
    }

    protected static String xpathLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }
        String[] parts = value.split("'", -1);
        StringBuilder sb = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append(", \"'\", ");
            sb.append("'").append(parts[i]).append("'");
        }
        return sb.append(")").toString();
    }

}