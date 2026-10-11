import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    protected CartPage cartPage;
    protected String itemName = "Sauce Labs Backpack";

    @BeforeMethod
    public void prepareCart() {
        InventoryPage inventory = new LoginPage(driver)
                .openLoginPage()
                .login("standard_user", "secret_sauce");
        inventory.addToCart(itemName);
        cartPage = inventory.header().openCart();
    }

    @Test
    public void itemIsInCart() {
        Assert.assertTrue(cartPage.containsCartItem(itemName),
                "Товар должен быть в корзине");
    }

    @Test
    public void removeItemClearsCart() {
        cartPage.removeItem(itemName);
        Assert.assertTrue(cartPage.isCartEmpty(),
                "После удаления корзина должна быть пуста");
    }
}
