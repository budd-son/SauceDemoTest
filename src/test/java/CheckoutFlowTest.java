import com.nekhaichik.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFlowTest extends BaseTest{
    protected String itemName = "Sauce Labs Backpack";
    protected String firstName = "user";
    protected String lastName = "swan";
    protected String postalCode = "1234";
    @Test
    public  void checkoutFlowTest()
    {
        CheckoutStepTwoPage page= new LoginPage(driver)
                .openLoginPage()
                .login("standard_user","secret_sauce")
                .addToCart(itemName)
                .header()
                .openCart()
                .openCheckout()
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputPostalCode(postalCode)
                .clickContinue();
        Double expectTotalSum = page.getSubTotalSum() + page.getTaxSum();
        Assert.assertEquals(page.getTotalSum(), expectTotalSum, "Expected sum should be equal to the total sum");
        String actualHeader = page.finish().getCompleteHeader();
        String completeHeader = "Thank you for your order!";
        Assert.assertEquals(actualHeader, completeHeader, "Headers don't match");
    }
}
