import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BurgerMenuTest extends BaseTest  {

    protected InventoryPage inventory;
    protected String itemName = "Sauce Labs Backpack";

    @BeforeMethod
    public void login() {
        inventory = new LoginPage(driver)
                .openLoginPage()
                .login("standard_user", "secret_sauce");
    }

    @Test
    public void aboutOpensSauceLabsSite() {
        inventory.header().openBurgerMenu().openAbout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("saucelabs.com"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucelabs.com"),
                "Должны быть на Sauce Labs, а сейчас: " + currentUrl);
    }

    @Test
    public void resetAppStateClearsCart() {
        Assert.assertFalse(inventory.header().isCartBadgeDisplayed(),
                "Перед тестом корзина должна быть пуста");

        inventory.addToCart(itemName);
        Assert.assertEquals(inventory.header().getCartItemCount(), 1,
                "После добавления товара счётчик должен быть 1");
        Assert.assertEquals(inventory.getItemButtonText(itemName), "Remove",
                "После добавления кнопка должна быть 'Remove'");

        inventory.header().openBurgerMenu().resetAppState().closeBurgerMenu();

        Assert.assertEquals(inventory.header().getCartItemCount(), 0,
                "После Reset App State корзина должна быть пуста");

    }
}
