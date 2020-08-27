package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void get(String link) {
        driver.get(link);
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void type(By locator, String content) {
        find(locator).sendKeys(content);
    }

    public boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    public void click(By locator) {
        find(locator).click();
    }
}
