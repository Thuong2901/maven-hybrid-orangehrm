package browserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class ChromeBrowserMananger implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=C:\\Program Files\\Google\\Chrome\\Application");
        chromeOptions.addArguments("--profile-directory=Profile 4");
        return new ChromeDriver(chromeOptions);
    }
}
