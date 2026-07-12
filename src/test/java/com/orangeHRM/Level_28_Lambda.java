package com.orangeHRM;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import utilities.ExcelConfig;
import utilities.PropertiesConfig;


public class Level_28_Lambda extends BaseTest {
    EnvironmentConfig environmentConfig;

    @Parameters({ "server", "os", "browser", "browser_version" })
    @BeforeClass
    public void beforeClass(String serverName, String osName, String browserName, String browserVersion) {
        ConfigFactory.setProperty("environment",environment);
        environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        driver = getBrowserDriverLambda(environmentConfig.appUrl(), osName, browserName, browserVersion);

        loginPage = PageGenerator.getLoginPage(driver);

    }

    @Test
    public void Employee_01_NewEmployee() {


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
    private PropertiesConfig propertiesConfig;
    private String employeeID, adminUserName, adminPassword,employeeUsername,employeeEmail;

}