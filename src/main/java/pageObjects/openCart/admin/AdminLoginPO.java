package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminDashboardPageUI;
import pageUIs.openCart.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;
    public AdminLoginPO(WebDriver driver){
        this.driver =driver;
    }

    public void enterToUsername(String username) {
        waitElementVisible(driver, AdminLoginPageUI.USER_NAME);
        sendkeyToElement(driver, AdminLoginPageUI.USER_NAME,username);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, AdminLoginPageUI.PASS_WORD);
        sendkeyToElement(driver, AdminLoginPageUI.PASS_WORD,password);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPO.class,driver);
    }
}
