package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.AddEmployeePageUI;
import pageUIs.BasePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, textboxLabel);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, textboxLabel);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX, textboxLabel);
        return getElementDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementVisible(driver, AddEmployeePageUI.SAVE_BUTTON, textboxLabel);
        clickToElement(driver,AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
        return PageGenerator.getPage(PersonalDetailPageObject.class,driver);

    }
}