package browserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.GenericLogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        if (!GlobalConstants.OS_NAME.toUpperCase().contains("MAC")){
            throw new BrowserNotSupportedExeption("Safari is not supported on " + GlobalConstants.OS_NAME);
        }
        SafariOptions safariOptions = new SafariOptions();
        return new SafariDriver(safariOptions);
    }
}
