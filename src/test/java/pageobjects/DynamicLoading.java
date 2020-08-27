package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading extends BasePage {
    private By startBtnLocator = By.cssSelector("#start button");
    private By finishLocator = By.id("finish");

    public DynamicLoading(WebDriver driver) {
        super(driver);
    }

    public void loadExample(String number) {
        get("https://the-internet.herokuapp.com/dynamic_loading/" + number);
        click(startBtnLocator);
    }

    public boolean hiddenTextPresent() {
        return isDisplayed(finishLocator, 10);
    }
}
