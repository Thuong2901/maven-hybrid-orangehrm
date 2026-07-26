package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1920x1080");
        return new ChromeDriver(chromeOptions);
    }
}
