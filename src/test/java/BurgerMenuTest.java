import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BurgerMenuTest extends BaseTest {

    private static final String ITEM_NAME = "Sauce Labs Backpack";

    @BeforeMethod
    public void loginBeforeMethod() {
        loginSteps.openLoginPage().loginAs(USERNAME, PASSWORD);
    }
    @Test
    public void aboutOpensSauceLabsSite() {
        burgerMenuSteps.openAbout();

        Assert.assertTrue(burgerMenuSteps.getCurrentUrl().contains("saucelabs.com"),
                "Should be on Sauce Labs site");
    }

    @Test
    public void resetAppStateClearsCart() {
        Assert.assertFalse(inventorySteps.isCartBadgeDisplayed(),
                "Cart badge should not be displayed before test");

        inventorySteps.addToCart(ITEM_NAME);
        Assert.assertEquals(inventorySteps.getCartItemCount(), 1,
                "Cart badge should be 1 after adding one item");
        Assert.assertEquals(inventorySteps.getItemButtonText(ITEM_NAME), "Remove",
                "Button should be 'Remove' after adding");

        burgerMenuSteps.resetAppState();

        Assert.assertEquals(inventorySteps.getCartItemCount(), 0,
                "Cart badge should be 0 after Reset App State");
    }
}
