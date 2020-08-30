package tests;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestUsingGrid {
    WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        String url = "http://192.168.189.1:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(url), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void gridTest() {
        driver.get("http://the-internet.herokuapp.com/");
        Assert.assertThat(driver.getTitle(), CoreMatchers.is("The Internet"));
    }
}
