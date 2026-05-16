package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePageObject clickToAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver,EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(AddEmployeePageObject.class,driver);
    }

    public void clickToEditButton() {
        waitElementClickable(driver, EmployeeListPageUI.EDIT_BUTTON);
        clickToElement(driver,EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }
}