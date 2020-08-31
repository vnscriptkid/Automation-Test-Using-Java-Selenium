package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestRightClick {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/context_menu");
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testContextMenu() {
        WebElement hotSpot = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(hotSpot)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText(), is(equalTo("You selected a context menu")));
    }
}
