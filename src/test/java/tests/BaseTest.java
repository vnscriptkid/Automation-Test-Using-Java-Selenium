package tests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.URL;

import static tests.Config.*;

public class BaseTest {
    protected WebDriver driver;
    private String testName;

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if (host.equals("saucelabs")) {
                if (sauceKey == null || sauceUser == null) {
                    throw new Exception("Sauce credentials is not set up yet");
                }
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("username", sauceUser);
                capabilities.setCapability("accessKey", sauceKey);

                capabilities.setCapability("browserName", browserName);
                capabilities.setCapability("platform", platform);
                capabilities.setCapability("version", version);
                capabilities.setCapability("name", testName);

                driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities);
            } else if (host.equals("localhost")) {
                if (browserName.equals("firefox")) {
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
                    driver = new FirefoxDriver();
                } else if (browserName.equals("chrome")) {
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver.exe");
                    driver = new ChromeDriver();
                }
            }

        }

        @Override
        protected void after() {
            driver.quit();
        }
    };

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getDisplayName();
        }
    };
}
