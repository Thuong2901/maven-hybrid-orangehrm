package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;
    public void clickToPIMModule(WebDriver driver) {
        waitElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver,DashboardPageUI.PIM_MODULE);
    }
}
