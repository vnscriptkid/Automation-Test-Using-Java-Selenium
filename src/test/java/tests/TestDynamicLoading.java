package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.DynamicLoading;

public class TestDynamicLoading {
    private WebDriver driver;
    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        dynamicLoading = new DynamicLoading(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
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
