package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.Login;

public class TestLogin {
    private WebDriver driver;
    private Login login;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        login = new Login(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(
                login.successMessagePresent()
        );
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        Assert.assertTrue(
                login.failureMessagePresent()
        );
    }
}
