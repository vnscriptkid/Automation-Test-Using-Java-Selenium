package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.Config;

public class DynamicLoading extends BasePage {
    private By startBtnLocator = By.cssSelector("#start button");
    private By finishLocator = By.id("finish");

    public DynamicLoading(WebDriver driver) {
        super(driver);
    }

    public void loadExample(String number) {
        get(Config.baseUrl + "dynamic_loading/" + number);
        click(startBtnLocator);
    }

    public boolean hiddenTextPresent() {
        return isDisplayed(finishLocator, 10);
    }
}
