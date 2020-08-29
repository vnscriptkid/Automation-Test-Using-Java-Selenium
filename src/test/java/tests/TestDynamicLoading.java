package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pageobjects.DynamicLoading;
import tests.groups.Deep;

@Category(Deep.class)
public class TestDynamicLoading extends BaseTest {
    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver);
    }

    @Test
    public void hiddenElementDisplayed() {
        dynamicLoading.loadExample("1"); // locate, click
        Assert.assertTrue(dynamicLoading.hiddenTextPresent());
    }

    @Test
    public void newElementRendered() {
        dynamicLoading.loadExample("2"); // locate, click
        Assert.assertTrue(dynamicLoading.hiddenTextPresent());
    }
}
