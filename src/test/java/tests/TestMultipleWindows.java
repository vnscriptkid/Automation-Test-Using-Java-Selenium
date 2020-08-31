package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestMultipleWindows {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/windows");
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void naiveWindowSwitchingTest() throws InterruptedException {
        WebElement windowSwitchingButton = driver.findElement(By.cssSelector(".example a[target=_blank]"));
        windowSwitchingButton.click();
        Thread.sleep(2000);
        Object[] windows = driver.getWindowHandles().toArray();
        driver.switchTo().window(windows[0].toString());
        assertThat(driver.getTitle(), is(not("New Window")));
        driver.switchTo().window(windows[1].toString());
        assertThat(driver.getTitle(), is("New Window"));
    }

    @Test
    public void resilientTestApproach() throws InterruptedException {
        String originalWindow = driver.getWindowHandle();
        WebElement windowSwitchingButton = driver.findElement(By.cssSelector(".example a[target=_blank]"));
        windowSwitchingButton.click();
        Thread.sleep(2000);

        String newWindow = null;
        for (String window :
                driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                newWindow = window;
            }
        }

        driver.switchTo().window(originalWindow);
        assertThat(driver.getTitle(), is(not("New Window")));
        driver.switchTo().window(newWindow);
        assertThat(driver.getTitle(), is("New Window"));
    }
}
