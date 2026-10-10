import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test
    public void positiveLogin(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage
                .openLoginPage()
                .login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "URL должен содержать inventory.html, текущий:" + driver.getCurrentUrl());

    }
    @Test
    public void negativeLoginWithWrongPassword(){
        String error = new LoginPage(driver)
                .openLoginPage()
                .enterUserName("standard_user")
                .enterPassword("wrong_password")
                .clickLoginButton()
                .getError();
        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(error, expectedError, "Текст ошибки не совпадает");
    }
}
