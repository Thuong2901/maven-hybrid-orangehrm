package com.orangeHRM;

//import từ thư viện

import com.azure.core.http.rest.Page;
import core.BasePage;
import core.BaseTest;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.invokers.ParameterHolder;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_18_Pattern_Object extends BaseTest {



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
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToTextboxByLabel(driver,"Username",adminUserName);
        loginPage.enterToTextboxByLabel(driver,"Password",adminPassword);

        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage=PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToButtonByText(driver,"Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver,"Employee Id");


        addEmployeePage.clickToButtonByText(driver,"Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        verifyTrue(personalDetailPage.isToastMessageDisplay(driver,"Successfully Updated"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        //Fail
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"lastName"), employeeLastName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver,"firstName"), employeeFirstName );
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver,"Employee Id"), employeeID);

        personalDetailPage.selectDropdownByLabel(driver,"Nationality","British");

        personalDetailPage.clickToRadioByLabel(driver,"Enable");
        personalDetailPage.clickToRadioByLabel(driver,"Disable");
        personalDetailPage.clickToCheckboxByLabel(driver,"Create Login Details");

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