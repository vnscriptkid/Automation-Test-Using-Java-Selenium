package tests;

import org.apache.commons.io.FileUtils;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestScreenshot {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            File srcFile = ((TakesScreenshot) driver ).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("failshot_"
                        + description.getClassName() + "_"
                        + description.getMethodName() + ".png"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }
    };

    @Test
    public void OnError() {
        driver.get("http://the-internet.herokuapp.com");
        assertThat(false, is(true));
    }
}
