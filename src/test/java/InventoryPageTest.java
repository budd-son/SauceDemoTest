import com.nekhaichik.pages.InventoryPage;
import com.nekhaichik.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class InventoryPageTest extends BaseTest{
    protected InventoryPage inventoryPage;
    protected String itemName = "Sauce Labs Backpack" ;

    @BeforeMethod
    public void login(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        inventoryPage = loginPage.login("standard_user","secret_sauce");
    }
    @Test
    public void sortingByPriceLohi(){
        inventoryPage.selectSortOption("Price (low to high)");
        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "Цены отсортированы неверно");
    }
    @Test
    public void sortingByPriceHilo(){
        inventoryPage.selectSortOption("Price (high to low)");
        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Цены отсортированы неверно");
    }
    @Test
    public void addItemChangeIconCart() {
        inventoryPage.addToCart(itemName);
        Assert.assertEquals(inventoryPage.header().getCartItemCount(), 1,
                "После добавления товара счётчик должен быть 1");
    }

    @Test
    public void addItemChangeBtnToRemove(){
        String before = inventoryPage.getItemButtonText(itemName);
        Assert.assertEquals(before, "Add to cart",
                "До добавления кнопка должна называться 'Add to cart'");

        inventoryPage.addToCart(itemName);

        String after = inventoryPage.getItemButtonText(itemName);
        Assert.assertEquals(after, "Remove",
                "После добавления кнопка должна называться 'Remove'");
    }

    @Test
    public void buttonChangesToRemoveAfterAdd() {
        Assert.assertEquals(inventoryPage.getItemButtonText(itemName), "Add to cart");
        inventoryPage.addToCart(itemName);
        Assert.assertEquals(inventoryPage.getItemButtonText(itemName), "Remove");
    }

}

