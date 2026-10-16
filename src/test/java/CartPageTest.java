import com.nekhaichik.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    private static final String ITEM_NAME = "Sauce Labs Backpack";

    @BeforeMethod
    public void prepareCart() {
        loginSteps.openLoginPage().loginAs(USERNAME, PASSWORD);
        inventorySteps.addToCart(ITEM_NAME);
        inventorySteps.openCart();
    }

    @Test
    public void itemIsInCart() {
        Assert.assertTrue(cartSteps.containsItem(ITEM_NAME),
                "Item should be present in the cart");
    }

    @Test
    public void removeItemClearsCart() {
        cartSteps.removeItem(ITEM_NAME);

        Assert.assertTrue(cartSteps.isCartEmpty(),
                "Cart should be empty after removing the only item");
    }
}