package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementInvisible(driver, AddEmployeePageUI.FISRT_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.FISRT_NAME_TEXTBOX,firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementInvisible(driver, AddEmployeePageUI.FISRT_NAME_TEXTBOX);
        sendKeyToElement(driver,AddEmployeePageUI.FISRT_NAME_TEXTBOX,lastName);
    }

    public String getEmployeeID(String s) {
        waitElementInvisible(driver,AddEmployeePageUI.EMPLOYEE_TEXTBOX);
        return getElementDOMProperty(driver,AddEmployeePageUI.EMPLOYEE_TEXTBOX,"value");
    }

    public void clickToSaveButton() {
        waitElementClickable(driver,AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver,AddEmployeePageUI.SAVE_BUTTON);
    }
}
