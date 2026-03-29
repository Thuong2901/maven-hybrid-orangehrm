package com.orangeHRM;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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


public class Level_19_Element_Undisplay extends BaseTest {



    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUserName = "automation";
        adminPassword = "Auto123$$##";
        employeeUsername = "john" + getRandomNumber();
        employeePassword = "Auto11223@";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Maintenance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));



        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToButtonByText(driver,"Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver,"Employee Id");
        addEmployeePage.clickToButtonByText(driver,"Create login details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver,"Password",employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",employeePassword);

        employeeListPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);


        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName );
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        //Logout
        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",employeePassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));

        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnUnDisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnUnDisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnUnDisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnUnDisplayed(driver,"Maintenance"));

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
    private String employeeUsername,employeePassword;
}