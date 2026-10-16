import com.nekhaichik.steps.InventorySteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        InventorySteps inventory = loginSteps.openLoginPage().loginAs(USERNAME, PASSWORD);

        Assert.assertTrue(inventory.isOpened(),
                "Inventory page should be opened after successful login");
    }

    @Test
    public void userCannotLoginWithWrongPassword() {
        loginSteps.openLoginPage().loginAs(USERNAME, "wrong_password");

        Assert.assertEquals(loginSteps.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message does not match");
    }
}
