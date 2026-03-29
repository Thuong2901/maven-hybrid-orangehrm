package com.orangeHRM;

//import từ thư viện

import com.aventstack.extentreports.Status;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import reportConfig.ChainTestListener;
import reportConfig.ExtentManager;

import java.lang.reflect.Method;

import static jdk.nio.zipfs.ZipFileAttributeView.AttrID.method;


public class Level_15_ChainTest extends BaseTest {


    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "automation";
        adminPassword = "Auto123$$##";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void TC_01_CreateNewEmployee() {
        ChainTestListener.log("NewEmploy-STEP 01- Enter to username and Password with info" + adminUserName +  "|" + adminPassword);
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);

        takeScreenshot();

        ChainTestListener.log("NewEmploy-STEP 02- Navigate to Dashboard page" );
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        takeScreenshot();

        ChainTestListener.log("NewEmploy-STEP 03- Navigate to Employee Search page" );

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("NewEmploy-STEP 04- Navigate to Add Employee page" );

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        takeScreenshot();

        ChainTestListener.log("NewEmploy-STEP 05- Enter to FirstName and LastName with info" + employeeFirstName +  "|" + employeeLastName);

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        ChainTestListener.log("NewEmploy-STEP 06- Navigate to Personal Details page" );

        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);


    }




    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
}