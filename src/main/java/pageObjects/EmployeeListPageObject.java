package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.EmployeeListUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;
    public void clickToAddEmployeeButton(WebDriver driver) {
        this.driver = driver;
    }
    public void cliclToAddEmployeeButton(){
        waitElementClickable(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver,EmployeeListUI.ADD_EMPLOYEE_BUTTON);

    }
}
