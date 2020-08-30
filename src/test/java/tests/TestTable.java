package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class TestTable {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/tables");
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testFirstTableSortedAscendingOrder() {
        // locate the Due head
        WebElement dueButton = driver.findElement(By.cssSelector("#table1 thead th:nth-of-type(4)"));
        // click
        dueButton.click();
        // locate values in Due column
        List<WebElement> dueElements = driver.findElements(By.cssSelector("#table1 tbody tr td:nth-of-type(4)"));
        List<Double> dueValues = new LinkedList<>();

        for (WebElement element :
                dueElements) {
            dueValues.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        // make sure they are in order
        for (int i = 0; i < dueValues.size() - 1; i++) {
            assertTrue(dueValues.get(i) <= dueValues.get(i + 1));
        }
    }

    @Test
    public void testFirstTableSortedDescendingOrder() {
        // locate the Due head
        WebElement dueButton = driver.findElement(By.cssSelector("#table1 thead th:nth-of-type(4)"));
        // click
        dueButton.click();
        dueButton.click();
        // locate values in Due column
        List<WebElement> dueElements = driver.findElements(By.cssSelector("#table1 tbody tr td:nth-of-type(4)"));
        List<Double> dueValues = new LinkedList<>();

        for (WebElement element :
                dueElements) {
            dueValues.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        // make sure they are in order
        for (int i = 0; i < dueValues.size() - 1; i++) {
            assertTrue(dueValues.get(i) >= dueValues.get(i + 1));
        }
    }

    @Test
    public void testFirstTableEmailSorted() {
        // locate the Due head
        WebElement dueButton = driver.findElement(By.cssSelector("#table1 thead th:nth-of-type(3)"));
        // click
        dueButton.click();
        // locate emails
        List<WebElement> emailElements = driver.findElements(By.cssSelector("#table1 tbody tr td:nth-of-type(3)"));

        for (int i = 0; i < emailElements.size() - 1; i++) {
            String currentEmail = emailElements.get(i).getText();
            String nextEmail = emailElements.get(i + 1).getText();
            assertTrue(currentEmail.compareTo(nextEmail) < 0);
        }
    }

    @Test
    public void testSecondTableWebsitesSorted() {
        WebElement websiteButton = driver.findElement(By.cssSelector("#table2 thead .web-site"));
        websiteButton.click();

        List<WebElement> websiteElements = driver.findElements(By.cssSelector("#table2 tbody .web-site"));
        for (int i = 0; i < websiteElements.size() - 1; i++) {
            String current = websiteElements.get(i).getText();
            String next = websiteElements.get(i + 1).getText();
            assertTrue(current.compareTo(next) < 0);
        }
    }
}
