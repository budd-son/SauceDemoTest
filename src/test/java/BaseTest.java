import com.nekhaichik.steps.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected LoginSteps loginSteps;
    protected CartSteps cartSteps;
    protected InventorySteps inventorySteps;
    protected CheckoutSteps checkoutSteps;
    protected BurgerMenuSteps burgerMenuSteps;
    protected static final String USERNAME = "standard_user";
    protected static final String PASSWORD = "secret_sauce";

    @BeforeClass
    public void setUpDriverBinary() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        loginSteps = new LoginSteps(driver);
        cartSteps = new CartSteps(driver);
        inventorySteps = new InventorySteps(driver);
        checkoutSteps = new CheckoutSteps(driver);
        burgerMenuSteps = new BurgerMenuSteps(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}