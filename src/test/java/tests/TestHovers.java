package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestHovers {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/hovers");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void hoverOnOneImage() {
        WebElement secondImage = driver.findElement(By.cssSelector(".figure:nth-of-type(2)"));
        Actions builder = new Actions(driver);
        builder.moveToElement(secondImage).build().perform();

        WebElement secondCaption = driver.findElements(By.className("figcaption")).get(1);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(secondCaption));

        assertThat(secondCaption.isDisplayed(), is(Boolean.TRUE));
        assertThat(secondCaption.findElement(By.tagName("h5")).getText(), containsString("user2"));
    }
}
