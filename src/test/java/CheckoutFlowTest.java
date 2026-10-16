import com.nekhaichik.pages.CheckoutCompletePage;
import com.nekhaichik.pages.CheckoutStepTwoPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFlowTest extends BaseTest {

    private static final String ITEM_NAME = "Sauce Labs Backpack";
    private static final String FIRST_NAME = "user";
    private static final String LAST_NAME = "swan";
    private static final String POSTAL_CODE = "1234";
    private static final String COMPLETE_HEADER = "Thank you for your order!";

    @BeforeMethod
    public void prepareCheckout() {
        loginSteps.openLoginPage()
                .loginAs(USERNAME, PASSWORD)
                .addToCart(ITEM_NAME)
                .openCart().proceedToCheckout();
    }

    @Test
    public void checkoutFlow() {
        checkoutSteps.fillCustomerInfo(FIRST_NAME, LAST_NAME, POSTAL_CODE);

        CheckoutStepTwoPage overview = checkoutSteps.continueToOverview();

        Assert.assertEquals(overview.getTotal(), overview.getSubTotal() + overview.getTax(), 0.001,
                "Total should equal subtotal + tax");

        CheckoutCompletePage complete = checkoutSteps.finish();
        Assert.assertEquals(complete.getCompleteHeader(), COMPLETE_HEADER,
                "Complete header does not match");
    }
}