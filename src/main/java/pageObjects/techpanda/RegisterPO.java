package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.techpanda.RegisterPageUI;

public class RegisterPO extends BasePage {
    private WebDriver driver;
     public RegisterPO(WebDriver driver){
         this.driver =driver;
     }

    public void enterFirstName(String firstName) {
         waitElementVisible(driver,RegisterPageUI.FIRST_NAME_TXT,firstName);
         sendkeyToElement(driver,RegisterPageUI.FIRST_NAME_TXT,firstName);
    }

    public void enterLastName(String lastName) {
        waitElementVisible(driver,RegisterPageUI.LAST_NAME_TXT,lastName);
        sendkeyToElement(driver,RegisterPageUI.LAST_NAME_TXT,lastName);
    }

    public void enterToEmail(String emailAddress) {
        waitElementVisible(driver,RegisterPageUI.EMAIL_TXT,emailAddress);
        sendkeyToElement(driver,RegisterPageUI.EMAIL_TXT,emailAddress);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver,RegisterPageUI.PASSWORD_TXT,password);
        sendkeyToElement(driver,RegisterPageUI.PASSWORD_TXT,password);
    }

    public void enterToConfirmPassword(String confirmPassword) {
        waitElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_TXT,confirmPassword);
        sendkeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TXT,confirmPassword);
    }

    public void enterToRegisterButton() {
         waitElementClickable(driver,RegisterPageUI.REGISTER_BTN);
         clickToElement(driver,RegisterPageUI.REGISTER_BTN);
    }

    public MyAccountPO acceptContinueAlert() {
         acceptToAlert(driver);
         return PageGenerator.getPage(MyAccountPO.class,driver);
    }
}
