package com.orangeHRM;

//import từ thư viện

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
import testdata.orangehrm.EmployeeData;
import utilities.DataConfigNet;


public class Level_25_DataTest_III_Static extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        employeeID = String.valueOf(getRandomNumber());

        loginPage.enterToTextboxByLabel(driver,"Username", EmployeeData.ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver,"Password",EmployeeData.ADMIN_PASSWORD);

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

        addEmployeePage.enterToTextboxByName(driver,"firstName",EmployeeData.FIRST_NAME);
        addEmployeePage.enterToTextboxByName(driver,"lastName",EmployeeData.LAST_NAME);

        //addEmployeePage.clearToTextboxByLabel(driver,"Employee Id");
        addEmployeePage.enterToTextboxByName(driver,"Employee Id",employeeID);

        addEmployeePage.clickToButtonByText(driver,"Create login details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",EmployeeData.USER_NAME);
        addEmployeePage.enterToTextboxByLabel(driver,"Password",EmployeeData.PASSWORD);
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",EmployeeData.PASSWORD);

        employeeListPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);


        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), EmployeeData.FIRST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), EmployeeData.LAST_NAME );
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",EmployeeData.USER_NAME);
        loginPage.enterToTextboxByLabel(driver,"Password",EmployeeData.PASSWORD);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver,"My info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"),EmployeeData.FIRST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"),EmployeeData.LAST_NAME);
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

}