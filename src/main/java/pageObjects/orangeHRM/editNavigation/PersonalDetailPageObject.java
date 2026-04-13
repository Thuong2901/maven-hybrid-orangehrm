package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailPageUI;

import java.awt.*;

public class PersonalDetailPageObject extends EditNavigatorPageObject {

    private WebDriver driver;

    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, textboxLabel);
        return getElementDOMProperty(driver,PersonalDetailPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, textboxLabel);
        return getElementDOMProperty(driver,PersonalDetailPageUI.LAST_NAME_TEXTBOX,"value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_TEXTBOX, textboxLabel);
        return getElementDOMProperty(driver,PersonalDetailPageUI.EMPLOYEE_TEXTBOX,"value");
    }


    public void clickToProfileImage() {
        waitElementClickable(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        clickToElement(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
    }

    public String getErrorMessageProfileImage(){
        waitElementVisible(driver, PersonalDetailPageUI.PROFILE_IMAGE_UPLOAD_ERROR_MESSAGE);
        return getElementDOMProperty(driver,PersonalDetailPageUI.PROFILE_IMAGE_UPLOAD_ERROR_MESSAGE,"value");
    };

    public Dimension getProfileNaturalImageSize() {
        waitElementVisible(driver, PersonalDetailPageUI.EDIT_PROFILE_IMAGE);
        int x =Integer.parseInt(getElementDOMProperty(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE,"naturalHeight"));
        int y =Integer.parseInt(getElementDOMProperty(driver,PersonalDetailPageUI.EDIT_PROFILE_IMAGE,"naturalWidth"));
        return new Dimension(x,y);

    }
}