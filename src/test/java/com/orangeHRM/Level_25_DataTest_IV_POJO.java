package com.orangeHRM;

//import từ thư viện

import core.BaseTest;
import core.GlobalConstants;
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
import testdata.orangehrm.EmployeeData;
import testdata.orangehrm.Employee_Data;


public class Level_25_DataTest_IV_POJO extends BaseTest {


    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        employeeData =Employee_Data.getEmployeeData();
        employeeID = String.valueOf(getRandomNumber());
        employeeData.setFirstName("john");
        employeeData.setLastName("Kendi");
        employeeData.setUserName("john.Kendi");
        employeeData.setPassword("Auto1234a@");

        loginPage.enterToTextboxByLabel(driver,"Username", GlobalConstants.ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver,"Password",GlobalConstants.ADMIN_PASSWORD);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        Assert.assertFalse(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyFalse(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
    }

    @Test
    public void Employee_01_NewEmployee() {

        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToButtonByText(driver,"Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeData.getFirstName());
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeData.getLastName());

        //addEmployeePage.clearToTextboxByLabel(driver,"Employee Id");
        addEmployeePage.enterToTextboxByName(driver,"Employee Id",employeeID);

        addEmployeePage.clickToButtonByText(driver,"Create login details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",employeeData.getUserName());
        addEmployeePage.enterToTextboxByLabel(driver,"Password",employeeData.getPassword());
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",employeeData.getPassword());

        employeeListPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);


        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeData.getFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeData.getLastName());
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",employeeData.getUserName());
        loginPage.enterToTextboxByLabel(driver,"Password",employeeData.getPassword());

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver,"My info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"),employeeData.getFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"),employeeData.getLastName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"Employee Id"),employeeID);

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
    private String employeeID;
    private Employee_Data employeeData;

}