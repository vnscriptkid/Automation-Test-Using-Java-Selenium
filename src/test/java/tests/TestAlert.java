package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestAlert {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void makeSureFirstButtonWorksCorrectly() {
        WebElement firstButton = driver.findElement(By.cssSelector("ul li:nth-of-type(1) button"));

        firstButton.click();
        Alert popup = driver.switchTo().alert();
        popup.accept();
        WebElement result = driver.findElement(By.id("result"));
        assertThat(result.getText(), is(equalTo("You successfuly clicked an alert")));
    }

    @Test
    public void makeSureSecondButtonWorksCorrectly() {
        WebElement secondButton = driver.findElement(By.cssSelector("ul li:nth-of-type(2) button"));

        WebElement result = driver.findElement(By.id("result"));

        secondButton.click();
        Alert popup = driver.switchTo().alert();
        popup.accept();
        assertThat(result.getText(), is(equalTo("You clicked: Ok")));

        secondButton.click();
        driver.switchTo().alert().dismiss();
        assertThat(result.getText(), is(equalTo("You clicked: Cancel")));
    }

    @Test
    public void makeSureThirdButtonWorksCorrectly() {
        WebElement thirdButton = driver.findElement(By.cssSelector("ul li:nth-of-type(3) button"));

        thirdButton.click();
        Alert popup = driver.switchTo().alert();
        popup.sendKeys("it's me");
        popup.accept();

        WebElement result = driver.findElement(By.id("result"));
        assertThat(result.getText(), is(equalTo("You entered: it's me")));
    }
}
