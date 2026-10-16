package com.nekhaichik.pages;

import com.nekhaichik.component.HeaderComponent;
import org.openqa.selenium.WebDriver;

public abstract class BaseLoggedInPage extends BasePage {
    protected final HeaderComponent header;

    protected BaseLoggedInPage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }
    public  HeaderComponent header() {
        return header;
    }
}