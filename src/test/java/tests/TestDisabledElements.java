package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TestDisabledElements {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDisabledDropdownItem() {
        WebElement firstOption = driver.findElement(By.cssSelector("#dropdown option"));
        System.out.println(firstOption.getAttribute("disabled"));
        assertTrue(firstOption.getAttribute("disabled").equals("true"));
    }

    @Test
    public void anotherWay() {
        Select select = new Select(driver.findElement(By.id("dropdown")));
        assertThat(select.getOptions().get(0).isDisplayed(), is(true));
        assertThat(select.getOptions().get(0).isEnabled(), is(false));
    }
}
