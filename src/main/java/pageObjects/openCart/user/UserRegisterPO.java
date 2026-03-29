package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;
    public UserRegisterPO(WebDriver driver){
        this.driver =driver;
    }


    public void enterToFirstName(String firstname) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, textboxLabel);
        sendkeyToElement(driver,UserRegisterPageUI.FIRST_NAME_TEXTBOX,firstname);
    }

    public void enterToLastName(String lastname) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, textboxLabel);
        sendkeyToElement(driver,UserRegisterPageUI.LAST_NAME_TEXTBOX,lastname);
    }

    public void enterToEmailAddress(String email) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX, textboxLabel);
        sendkeyToElement(driver,UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX,email);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, textboxLabel);
        sendkeyToElement(driver,UserRegisterPageUI.PASSWORD_TEXTBOX,password);
    }

    public void acceptPrivacyCheckbox() {
        waitElementClickable(driver,UserRegisterPageUI.AGREE_CHECKBOX);
        checkToCheckbox(driver,UserRegisterPageUI.AGREE_CHECKBOX);
    }


    public UserHomePO clickToContinueButton() {
        waitElementClickable(driver,UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver,UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplayed() {
        waitElementClickable(driver,UserRegisterPageUI.CREATED_ACCOUNT_MESSAGE_SUCCESS);
        return isElementDisplayed(driver,UserRegisterPageUI.CREATED_ACCOUNT_MESSAGE_SUCCESS);
    }
}
