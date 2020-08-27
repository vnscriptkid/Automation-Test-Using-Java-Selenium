package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage {

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By submitBtnLocator = By.cssSelector("button[type=submit]");
    private By successMessageLocator = By.cssSelector(".flash.success");
    private By formLocator = By.id("login");
    private By failedMessageLocator = By.cssSelector(".flash.error");

    public Login(WebDriver driver) throws Exception {
        super(driver);
        get("https://the-internet.herokuapp.com/login");
        if (!isDisplayed(formLocator, 10)) {
            throw new Exception("Page is not ready");
        }
    }

    public void with(String username, String password) {
        type(usernameLocator, username); // TODO: can't not find element
        type(passwordLocator, password);
        click(submitBtnLocator); //
    }

    public boolean successMessagePresent() {
        return isDisplayed(successMessageLocator, 10);
    }

    public boolean failureMessagePresent() {
        return isDisplayed(failedMessageLocator, 10);
    }
}
