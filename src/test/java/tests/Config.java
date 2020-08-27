package tests;

public class Config {
    public static final String baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com/");
    public static final String browserName = System.getProperty("browserName", "chrome");
}
