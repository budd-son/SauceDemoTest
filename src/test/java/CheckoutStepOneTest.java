import com.nekhaichik.pages.CartPage;
import com.nekhaichik.pages.CheckoutStepOnePage;
import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutStepOneTest extends BaseTest{
    protected CheckoutStepOnePage csoPage;
    protected String errorMessageFirstName = "Error: First Name is required";
    protected String errorMessageLastName = "Error: Last Name is required";
    protected String errorMessagePostalCode = "Error: Postal Code is required";
    protected String firstName = "firstName";
    protected String lastName = "lastName";
    protected String postalCode = "postalCode";
    protected String itemName = "Sauce Labs Backpack";
    @BeforeMethod
    public void prepareCart() {
        InventoryPage inventory = new LoginPage(driver)
                .openLoginPage()
                .login("standard_user", "secret_sauce");
        inventory.addToCart(itemName);
        csoPage = inventory.header().openCart().openCheckout();
    }
    @Test
    public void missFirstName() {
        String errMessage = csoPage.inputLastName(lastName).inputPostalCode(postalCode).clickContinueExpectingError().getError();
        Assert.assertEquals(errMessage, errorMessageFirstName, errorMessageFirstName );
    }
    @Test
    public void missLastName() {
        String errMessage = csoPage.inputFirstName(firstName).inputPostalCode(postalCode).clickContinueExpectingError().getError();
        Assert.assertEquals(errMessage, errorMessageLastName, errorMessageLastName );

    }
    @Test
    public void missPostalCode() {
        String errMessage = csoPage.inputFirstName(firstName).inputLastName(lastName).clickContinueExpectingError().getError();
        Assert.assertEquals(errMessage, errorMessagePostalCode, errorMessagePostalCode );

    }


}

