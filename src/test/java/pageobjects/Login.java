package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private WebDriver driver;

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By submitBtnLocator = By.cssSelector("button[type=submit]");
    private By successMessageLocator = By.cssSelector(".flash.success");
    private By formLocaltor = By.id("login");
    private By failedMessageLocator = By.cssSelector(".flash.error");

    public Login(WebDriver driver) throws Exception {
        this.driver = driver;
        driver.get("https://the-internet.herokuapp.com/login");
        if (!driver.findElement(formLocaltor).isDisplayed()) {
            throw new Exception("Page is not ready");
        }
    }

    public void with(String username, String password) {
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(submitBtnLocator).click();
    }

    public boolean successMessagePresent() {
        return driver.findElement(successMessageLocator).isDisplayed();
    }

    public boolean failureMessagePresent() {
        return driver.findElement(failedMessageLocator).isDisplayed();
    }
}
