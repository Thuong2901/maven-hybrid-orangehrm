
package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.orangeHRM.EmployeeListPageObject;

import java.util.List;

public class DashboardPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='PIM']/parent::a")
    private WebElement pimModule;

    @FindBy(xpath = "//span[text()='PIM']/parent::a")
    private List<WebElement> pimModuleList;

    private List<WebElement> loadingSpinner;

    @FindBy(xpath = "//aside//a")
    private List<WebElement> menuItems;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EmployeeListPageObject clickToPIMModule() {
        waitElementClickable(driver, pimModule);
        clickToElement(pimModule);
        return null;
    }

    public DashboardPageObject clickToModuleByTextInMenuItem(
            WebDriver driver, String moduleName) {
        for (WebElement item : menuItems) {
            if (item.getText().trim().equals(moduleName)) {
                waitElementClickable(driver, item);
                clickToElement(item);
                break;
            }
        }
        return this;
    }

    public boolean isModuleByTextInMenuItemDisplayed(
            WebDriver driver, String moduleName) {
        for (WebElement item : menuItems) {
            if (item.getText().trim().equals(moduleName)) {
                return item.isDisplayed();
            }
        }
        return false;
    }

    public boolean isModuleByTextInMenuItemUnUnDisplayed(
            WebDriver driver, String moduleName) {
        return !isModuleByTextInMenuItemDisplayed(driver, moduleName);
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
