package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSelectFromDropdown {

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
    public void dropdownTest() {
        WebElement option2 = driver.findElement(By.cssSelector("#dropdown option[value=\"2\"]"));
        option2.click();
        assertThat(option2.isSelected(), is(true));
    }

    @Test
    public void anotherApproach() {
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 2");
        assertThat(select.getFirstSelectedOption().getText(), is(equalTo("Option 2")));
    }
}
