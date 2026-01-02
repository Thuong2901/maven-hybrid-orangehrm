package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.EmployeeListPageUI;

import java.util.List;

public class EmployeeListPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;

    private List<WebElement> loadingSpinner;
    public EmployeeListPageObject(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickToAddEmployeeButton() {

        waitElementClickable(driver,addEmployeeButton);
        clickToElement(addEmployeeButton);

    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver,loadingSpinner);
    }
}