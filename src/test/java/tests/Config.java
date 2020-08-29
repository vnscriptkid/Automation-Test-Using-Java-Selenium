package tests;

public class Config {
    public static final String baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com/");
    public static final String host = System.getProperty("host", "localhost"); // or sauceLabs
    public static final String browserName = System.getProperty("browserName", "chrome");
    public static final String version = System.getProperty("browserVersion", "11.1");
    public static final String platform = System.getProperty("platformName", "macOS 10.13");
    public static final String sauceUser = System.getenv("SAUCE_USERNAME");
    public static final String sauceKey = System.getenv("SAUCE_ACCESS_KEY");
}
// Safari 11.1 macOS 10.13
