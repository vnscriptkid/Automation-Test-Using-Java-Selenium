package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestBasicAuth {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testPutCredentialsInUrl() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String congratsText = driver.findElement(By.cssSelector(".example p")).getText();
        assertThat(congratsText, startsWith("Congratulations!"));
    }
}
