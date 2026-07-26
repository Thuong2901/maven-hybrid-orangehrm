package environmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserStackEnvironment implements EnvironmentFactory{
    private WebDriver driver;
    private  String osName, osVersion, browserName, browserVersion;

    public BrowserStackEnvironment(WebDriver driver, String osName, String osVersion, String browserName, String browserVersion) {
        this.driver = driver;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDirver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String,Object> bstackOptions = new HashMap<>();

        capabilities.setCapability("browserName", browserName);
        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("browserVersion", browserVersion);
        bstackOptions.put("userName", GlobalConstants.BROWSER_STACK_USERNAME);
        bstackOptions.put("accessKey", GlobalConstants.BROWSER_STACK_AUTOMATE_KEY);
        bstackOptions.put("seleniumVersion", "4.29.0");
        bstackOptions.put("projectName", "Nopcommerce");
        bstackOptions.put("buildName","Automation");
        capabilities.setCapability("bstack:options",bstackOptions);

        try {
            driver = new RemoteWebDriver(
                    new URL(GlobalConstants.SAUCE_URL),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return driver;
    }
}
