package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestGrowl {
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

    @Test
    public void growlTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/");

        js.executeScript("if (!window.jQuery) {" +
                "var jquery = document.createElement('script');" +
                "jquery.type = 'text/javascript';" +
                "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';" +
                "document.getElementsByTagName('head')[0].appendChild(jquery)" +
                "}");

        js.executeScript("$.getScript('http://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

        js.executeScript("$('head').append('<link rel=\"stylesheet\"" +
                "href=\"http://the-internet.herokuapp.com/css/jquery.growl.css\" " +
                "type=\"text/css\"" +
                "/>');");

        Thread.sleep(1000);
        js.executeScript("$.growl({ title: 'GET', message: '/' });");
        js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
        js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
        js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
        Thread.sleep(3000);
    }
}
