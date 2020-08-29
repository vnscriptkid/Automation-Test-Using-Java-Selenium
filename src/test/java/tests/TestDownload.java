package tests;

import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.UUID;

public class TestDownload {
    WebDriver driver;
    File folder;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", folder.getAbsolutePath());
        profile.setPreference("browser.download.folderList", 2); // 0: desktop, 1: default, 2: custom
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "image/jpeg, application/pdf, application/octet-stream");
        profile.setPreference("pdfjs.disabled", true);
        options.setProfile(profile);
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
        for (File file :
                folder.listFiles()) {
            file.delete();
        }
        folder.delete();
    }

    @Test
    public void testDownloadFileOnFirefox() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click(); // click the first link
        Thread.sleep(2000);
        File[] listOfFiles = folder.listFiles();
        assertThat(folder, is(not(0)));
        for (File file: folder.listFiles()) {
            assertThat(file, is(not(0)));
        }
    }
}
