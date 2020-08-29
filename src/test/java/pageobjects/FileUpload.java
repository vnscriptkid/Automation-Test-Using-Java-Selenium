package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.Config;

import java.io.File;
import java.io.IOException;

public class FileUpload extends BasePage {

    private By formLocator = By.cssSelector("form[method=POST]");
    private By uploadInputLocator = By.id("file-upload");
    private By submitBtnLocator = By.id("file-submit");
    private By uploadedFileLocator = By.id("uploaded-files");

    private File file;

    public FileUpload(WebDriver driver) throws Exception {
        super(driver);
        get(Config.baseUrl + "upload");
        if (!isDisplayed(formLocator)) {
            throw new Exception("Page is not ready yet");
        }
    }

    public void createFile(String fileName) throws IOException {
        file = new File(fileName);
        file.createNewFile();
    }

    public void uploadFile() {
        type(uploadInputLocator, file.getAbsolutePath());
        click(submitBtnLocator);
    }

    public void deleteFile() {
        file.deleteOnExit();
    }

    public boolean uploadedFilePresent() {
        return find(uploadedFileLocator).getText().equals(file.getName());
    }
}
