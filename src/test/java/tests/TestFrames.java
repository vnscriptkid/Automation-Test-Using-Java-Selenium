package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestFrames {
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
    public void workWithBasicFrames() {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        Assert.assertTrue(driver.findElement(By.id("content")).getText().equals("MIDDLE"));
    }

    @Test
    public void workWithPracticalFrames() {
        driver.get("http://the-internet.herokuapp.com/tinymce");
        driver.switchTo().frame("mce_0_ifr");
        WebElement editor = driver.findElement(By.id("tinymce"));
        String beforeText = editor.getText();
        editor.clear();
        editor.sendKeys("Hello World!");
        String afterText = editor.getText();
        assertThat(afterText, not(equalTo((beforeText))));

        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.cssSelector(".example h3")).getText(),
                is(equalTo("An iFrame containing the TinyMCE WYSIWYG Editor")));
    }

}
