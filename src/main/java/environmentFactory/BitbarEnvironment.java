package environmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BitbarEnvironment implements EnvironmentFactory{
    private WebDriver driver;
    private String platformName, platformVersion,  browserName,  browserVersion;

    public BitbarEnvironment(WebDriver driver, String platformName, String platformVersion, String browserName, String browserVersion) {
        this.driver = driver;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDirver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);

        HashMap<String, String> bitbarOptions = new HashMap<String, String>();
        bitbarOptions.put("project", "NopCommerce");
        bitbarOptions.put("testrun", "Run on " + platformName + " | " + platformVersion + " | " + browserName + " | " + browserVersion);
        bitbarOptions.put("apiKey", GlobalConstants.BITBAR_AUTOMATE_KEY);
        bitbarOptions.put("osVersion", platformVersion);

        if (platformName.contains("Windows") || platformName.contains("Linux")) {
            bitbarOptions.put("resolution", "1920x1080");
        } else {
            bitbarOptions.put("resolution", "1920x1200");
        }

        bitbarOptions.put("seleniumVersion", "4");

        capabilities.setCapability("bitbar:options", bitbarOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BITBAR_EU_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return driver;
    }
}
