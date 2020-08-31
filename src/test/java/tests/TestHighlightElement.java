package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestHighlightElement {
    WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private void highlightElement(WebElement element, int duration) throws InterruptedException {
        String originalStyles = element.getAttribute("style");
        js.executeScript(
                "arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: 2px solid red; border-style: dashed;"
        );

        if (duration > 0) {
            Thread.sleep(duration * 1000);
            js.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element,
                    "style",
                    originalStyles
            );
        }
    }

    @Test
    public void makeSureHighlightingWorks() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/large");
        WebElement target = driver.findElement(By.id("sibling-2.3"));
        highlightElement(target, 3);
    }
}
