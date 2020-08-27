package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.Login;

public class TestLogin extends BaseTest {
    private Login login;

    @Before
    public void setUp() throws Exception {
        login = new Login(driver);
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(login.successMessagePresent());
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        Assert.assertTrue(login.failureMessagePresent());
    }
}
