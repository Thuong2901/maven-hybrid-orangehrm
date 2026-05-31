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
import testdata.model.Employee;
import utilities.ExcelConfig;


public class Level_25_DataTest_VI_Excel extends BaseTest {



    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("Employee");

        adminUserName = "automation";
        adminPassword = "Auto123$$##";
        employeeID = String.valueOf(getRandomNumber());

        employeeUsername=excelConfig.getCellData("UserName" ,2) + getRandomNumber();
        employeeEmail=excelConfig.getCellData("EmailAddress" ,2) + getRandomNumber() +"@gmail.com";

        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

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

        addEmployeePage.enterToTextboxByName(driver,"firstName",excelConfig.getCellData("FirstName",2));
        addEmployeePage.enterToTextboxByName(driver,"lastName",excelConfig.getCellData("LastName",2));

        //addEmployeePage.clearToTextboxByLabel(driver,"Employee Id");
        addEmployeePage.enterToTextboxByName(driver,"Employee Id",employeeID);

        addEmployeePage.clickToButtonByText(driver,"Create login details");

        addEmployeePage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver,"Password",excelConfig.getCellData("Password",2));
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password",excelConfig.getCellData("Password",2));

        employeeListPage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(10);


        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), excelConfig.getCellData("LastName",2));
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), excelConfig.getCellData("FirstName",2));
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        loginPage =personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen employee vua tao
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",excelConfig.getCellData("Password",2));

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver,"My info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"),excelConfig.getCellData("FirstName",2));
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"),excelConfig.getCellData("LastName",2));
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
    private ExcelConfig excelConfig;
    private String employeeID, adminUserName, adminPassword,employeeUsername,employeeEmail;

}