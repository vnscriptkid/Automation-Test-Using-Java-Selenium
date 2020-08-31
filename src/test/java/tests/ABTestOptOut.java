package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ABTestOptOut {
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
    public void withCookieAfterVistingPage() {
        driver.get("http://the-internet.herokuapp.com/abtest");
        WebElement h3Heading = driver.findElement(By.tagName("h3"));
        assertThat(h3Heading.getText(), startsWith("A/B Test"));
        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.navigate().refresh();
        h3Heading = driver.findElement(By.tagName("h3"));
        assertThat(h3Heading.getText(), is(equalTo("No A/B Test")));
    }

    @Test
    public void setCookieBeforeVisitingPage() {
        driver.get("http://the-internet.herokuapp.com");
        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.get("http://the-internet.herokuapp.com/abtest");
        WebElement h3Heading = driver.findElement(By.tagName("h3"));
        assertThat(h3Heading.getText(), is(equalTo("No A/B Test")));
    }

    @Test
    public void putOptOutQueryIntoUrl() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
        driver.switchTo().alert().dismiss();
        Thread.sleep(5000);
        WebElement h3Heading = driver.findElement(By.tagName("h3"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(h3Heading));
        assertThat(h3Heading.getText(), is(equalTo("No A/B Test")));
    }
}
