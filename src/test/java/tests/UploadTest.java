package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.FileUpload;

import java.io.File;
import java.io.IOException;

public class UploadTest extends BaseTest {
    FileUpload fileUpload;

    @Before
    public void setUp() throws Exception {
        fileUpload = new FileUpload(driver);
    }

    @Test
    public void uploadFile() throws IOException {
        fileUpload.createFile("new.txt");
        fileUpload.uploadFile();
        Assert.assertTrue(fileUpload.uploadedFilePresent());
        fileUpload.deleteFile();
    }
}
