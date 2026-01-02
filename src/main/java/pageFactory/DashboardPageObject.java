package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.EmployeeListPageObject;

import java.util.List;

public class DashboardPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='PIM']/parent::a")
    private WebElement pimModule;

    private List<WebElement> loadingSpinner;
    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public EmployeeListPageObject clickToPIMModule() {

        waitElementClickable(driver,pimModule);
        clickToElement(pimModule);
        return null;
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}