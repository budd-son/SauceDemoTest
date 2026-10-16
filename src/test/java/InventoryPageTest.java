import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryPageTest extends BaseTest {

    private static final String ITEM_NAME = "Sauce Labs Backpack";
    private static final String ADD_TO_CART = "Add to cart";
    private static final String REMOVE = "Remove";

    @BeforeMethod
    public void loginBeforeMethod() {
        loginSteps.openLoginPage().loginAs(USERNAME, PASSWORD);
    }

    @DataProvider(name = "sortOptions")
    public Object[][] sortOptions() {
        return new Object[][] {
                { "Price (low to high)", Comparator.<Double>naturalOrder() },
                { "Price (high to low)", Comparator.<Double>reverseOrder() }
        };
    }

    @Test(dataProvider = "sortOptions")
    public void sortingByPrice(String option, Comparator<Double> comparator) {
        inventorySteps.sortBy(option);

        List<Double> actual = inventorySteps.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(comparator);

        Assert.assertEquals(actual, expected,
                "Prices are not sorted correctly for option: " + option);
    }

    @Test
    public void addingItemIncrementsCartBadge() {
        inventorySteps.addToCart(ITEM_NAME);

        Assert.assertEquals(inventorySteps.getCartItemCount(), 1,
                "Cart badge should be 1 after adding one item");
    }

    @Test
    public void itemButtonChangesToRemoveAfterAdding() {
        Assert.assertEquals(inventorySteps.getItemButtonText(ITEM_NAME), ADD_TO_CART,
                "Button should be 'Add to cart' before adding");

        inventorySteps.addToCart(ITEM_NAME);

        Assert.assertEquals(inventorySteps.getItemButtonText(ITEM_NAME), REMOVE,
                "Button should be 'Remove' after adding");
    }
}