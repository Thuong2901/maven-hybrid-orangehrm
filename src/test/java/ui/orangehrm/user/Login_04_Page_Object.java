package ui.orangehrm.user;

//import từ thư viện
import core.BasePage;
import core.BaseTest;
import javaSDET.Topic_01_KeyWords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

//import từ class/interface từ package khác


public class Login_04_Page_Object extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(Login_04_Page_Object.class);

    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName,String appUrl){
        driver = getBrowserDriver(browserName,appUrl);

        loginPage = new LoginPageObject(driver);
        adminUserName ="Admin";
        adminPassword ="admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }
    @Test
    public void Employee_01_CreateEmployee(){

        loginPage.enterToUserNameTextbox(adminUserName);
        loginPage.enterToUserPassWordTextbox(adminPassword);

        loginPage.clickToLoginButton();
        dashboardPage = new DashboardPageObject();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

        dashboardPage.clickToPIMModule(driver);
        employeeListPage = new EmployeeListPageObject();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToAddEmployeeButton(driver);
        addEmployeePage = new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID("");

        addEmployeePage.clickToSaveButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        PersonalDetailPO personalDetailPage = new PersonalDetailPO(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);

    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private String employeeID,adminUserName,adminPassword,employeeFirstName,employeeLastName;
}
