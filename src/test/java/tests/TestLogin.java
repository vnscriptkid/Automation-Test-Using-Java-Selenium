package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pageobjects.Login;
import tests.groups.Shallow;

public class TestLogin extends BaseTest {
    private Login login;

    @Before
    public void setUp() throws Exception {
        login = new Login(driver);
    }

    @Test
    @Category(Shallow.class)
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent());
    }

    @Test
    @Category(Shallow.class)
    public void failed() {
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.failureMessagePresent());
    }
}
