import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutStepOneTest extends BaseTest {

    private static final String ITEM_NAME = "Sauce Labs Backpack";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String POSTAL_CODE = "postalCode";

    private static final String ERROR_FIRST_NAME = "Error: First Name is required";
    private static final String ERROR_LAST_NAME = "Error: Last Name is required";
    private static final String ERROR_POSTAL_CODE = "Error: Postal Code is required";

    @BeforeMethod
    public void prepareCheckout() {
        loginSteps.openLoginPage()
                .loginAs(USERNAME, PASSWORD)
                .addToCart(ITEM_NAME)
                .openCart().proceedToCheckout();
    }

    @DataProvider(name = "missingFields")
    public Object[][] missingFields() {
        return new Object[][] {
                // firstName, lastName, postalCode, expectedError
                { "",           LAST_NAME,   POSTAL_CODE, ERROR_FIRST_NAME },
                { FIRST_NAME,   "",          POSTAL_CODE, ERROR_LAST_NAME },
                { FIRST_NAME,   LAST_NAME,   "",          ERROR_POSTAL_CODE }
        };
    }

    @Test(dataProvider = "missingFields")
    public void checkoutFailsWhenFieldIsMissing(String firstName, String lastName,
                                                String postalCode, String expectedError) {
        checkoutSteps
                .fillCustomerInfo(firstName, lastName, postalCode)
                .continueExpectingError();

        Assert.assertEquals(checkoutSteps.getError(), expectedError,
                "Error message does not match for missing field");
    }
}

