package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.PersonalDetailPageUI;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getElementText(driver,PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getElementText(driver,PersonalDetailPageUI.LAST_NAME_TEXTBOX);
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_TEXTBOX);
        return getElementText(driver,PersonalDetailPageUI.EMPLOYEE_TEXTBOX);
    }
}
