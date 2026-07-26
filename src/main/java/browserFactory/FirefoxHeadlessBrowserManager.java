package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("window-size=1920x1080");
        return new FirefoxDriver(firefoxOptions);
    }
}
