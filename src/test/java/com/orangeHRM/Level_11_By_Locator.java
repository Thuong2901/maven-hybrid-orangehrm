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
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Level_11_By_Locator extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        loginPage = PageGenerator.getPage(LoginPageObject.class,driver);
        adminUserName = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";
    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextbox(adminUserName);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToAddEmployeeButton();
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        addEmployeePage.clickToSaveButton();

        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);
    }

    @Test
    public void Employee_02_Dynamic_Page(){

        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorPageName("Contact Details");

        //Từ Contact sang Job

        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorPageName("Job");

        //Từ Job qua Dependent

        dependentsPage = (DependentsPageObject) jobPage.openEditNavigatorPageName("Dependents");

        //Từ denpendent sang Personal
        personalDetailPage = (PersonalDetailPageObject) dependentsPage.openEditNavigatorPageName("Personal Details");

        //Từ personalDetail sang Job

        jobPage = (JobPageObject) personalDetailPage.openEditNavigatorPageName("Job");
    }

    @Test
    public void Employee_03_Dynamic_Page(){
        personalDetailPage.openEditNavigatorPageName("Contact Details")
        contactDetailPage =PageGenerator.getPage(ContactDetailPageObject.class,driver);

                //Từ Contact sang Job

        contactDetailPage.openEditNavigatorPageName("Job");
        jobPage =PageGenerator.getPage(JobPageObject.class,driver);

        //Từ Job qua Dependent

        jobPage.openEditNavigatorPageName("Dependents");
        dependentsPage =PageGenerator.getPage(DependentsPageObject.class,driver);

        //Từ denpendent sang Personal
        dependentsPage.openEditNavigatorPageName("Personal Details");
        personalDetailPage =PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        //Từ personalDetail sang Job

        personalDetailPage.openEditNavigatorPageName("Job");
        jobPage =PageGenerator.getPage(JobPageObject.class,driver);
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage ;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName;
}