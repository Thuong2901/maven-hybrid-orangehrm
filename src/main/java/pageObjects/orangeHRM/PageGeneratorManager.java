package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.EmployeeListPageObject;
import pageFactory.orangeHRM.PersonalDetailPageObject;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;

public class PageGeneratorManager {

    private static final Logger log = LoggerFactory.getLogger(PageGeneratorManager.class);
    private static LoginPageObject loginPageObject;
    public static LoginPageObject getLoginPage(WebDriver driver){
        if (loginPageObject ==null){
            loginPageObject = new LoginPageObject(driver);
        }
        return loginPageObject;
    }

    /*public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }*/

    public static DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);
    }

    public static PersonalDetailPageObject getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailPageObject(driver);
    }

    public static ContactDetailPageObject getContactDetailPage(WebDriver driver){
        return new ContactDetailPageObject(driver);
    }
}
