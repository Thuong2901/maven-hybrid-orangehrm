package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(how = How.CSS,using = "input[name='username']")
    private WebElement usernameTextbox;

    @FindBy(name = "//input[@name='password']")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    public LoginPageObject(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver,usernameTextbox);
        sendkeyToElement(usernameTextbox,username);

    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver,passwordTextbox);
        sendkeyToElement(passwordTextbox,password);

    }

    public void clickToLoginButton() {
        waitElementVisible(driver,loginButton);
        clickToElement(loginButton);


    }
}