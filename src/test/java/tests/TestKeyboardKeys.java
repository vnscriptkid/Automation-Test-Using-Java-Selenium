package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestKeyboardKeys {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/key_presses");
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLocateAndSendKeyPress() {
        WebElement input = driver.findElement(By.id("target"));
        input.sendKeys(Keys.SPACE);
        assertThat(driver.findElement(By.id("result")).getText(), is("You entered: SPACE"));
    }

    @Test
    public void testPressWithoutLocating() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ALT).build().perform();
        assertThat(driver.findElement(By.id("result")).getText(), is("You entered: ALT"));
    }
}
