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

import java.util.List;

public class TestCheckboxes {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkBoxDiscoveryTest() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type=checkbox]"));
        assertTrue(checkboxes.size() == 2);

        System.out.println("getAttribute: checked");
        for (WebElement checkbox :
                checkboxes) {
            System.out.println(checkbox.getAttribute("checked"));
        }

        System.out.println("\nisSelected");
        for (WebElement checkbox :
                checkboxes) {
            System.out.println(checkbox.isSelected());
        }
    }

    @Test
    public void testUsingGetAttribute() {
        WebElement firstCheckbox = driver.findElement(By.cssSelector("input[type=checkbox]:nth-of-type(1)"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("input[type=checkbox]:nth-of-type(2)"));
        assertThat(firstCheckbox.getAttribute("checked"), is(nullValue()));
        assertThat(secondCheckbox.getAttribute("checked"), is("true"));
    }

    @Test
    public void testUsingIsSelected() {
        WebElement firstCheckbox = driver.findElement(By.cssSelector("input[type=checkbox]:nth-of-type(1)"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("input[type=checkbox]:nth-of-type(2)"));
        assertThat(firstCheckbox.isSelected(), is(false));
        assertThat(secondCheckbox.isSelected(), is(true));
    }

}
