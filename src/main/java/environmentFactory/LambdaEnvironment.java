package environmentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;

public class LambdaEnvironment implements EnvironmentFactory{
    private WebDriver driver;
    private String osName, browserName,  browserVersion;

    public LambdaEnvironment(WebDriver driver, String osName, String browserName, String browserVersion) {
        this.driver = driver;
        this.osName = osName;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDirver() {
        MutableCapabilities capability = null;

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setPlatformName(osName);
                fOptions.setBrowserVersion(browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setPlatformName(osName);
                cOptions.setBrowserVersion(browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setPlatformName(osName);
                eOptions.setBrowserVersion(browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setPlatformName(osName);
                sOptions.setBrowserVersion(browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        HashMap<String, Object> lambdaOptions = new HashMap<String, Object>();
        lambdaOptions.put("username", GlobalConstants.LAMBDA_USERNAME);
        lambdaOptions.put("accessKey", GlobalConstants.LAMBDA_AUTOMATE_KEY);
        lambdaOptions.put("visual", true);
        lambdaOptions.put("video", true);
        lambdaOptions.put("build", "nopcommerce-build");
        lambdaOptions.put("project", "NopCommerce - UI Automation Testing");
        lambdaOptions.put("name", "Run on " + osName + " | " + browserName + " | " + browserVersion + " | " + formater.format(calendar.getTime()));
        lambdaOptions.put("w3c", true);
        lambdaOptions.put("selenium_version", "4.29.0");
        lambdaOptions.put("resolution", "1920x1080");
        lambdaOptions.put("plugin", "java-testNG");

        capability.setCapability("LT:Options", lambdaOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.LAMBDA_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return driver;
    }
}
