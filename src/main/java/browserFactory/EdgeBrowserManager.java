package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        EdgeOptions edgeOptions= new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:\\Program Files (x86)\\Microsoft\\Edge\\Application");
        edgeOptions.addArguments("profile-directory=Profile 1");
        return new EdgeDriver(edgeOptions);
    }
}
