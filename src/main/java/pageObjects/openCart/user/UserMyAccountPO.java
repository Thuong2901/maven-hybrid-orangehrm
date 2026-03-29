package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserMyAccountPageUI;

public class UserMyAccountPO extends BasePage {
    private WebDriver driver;

    public UserMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMyAccountPageDisplayed() {
        waitElementVisible(driver, UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB, textboxLabel);
        return isElementDisplayed(driver,UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB);
    }
}
