package com.orangeHRM;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.DashboardPageObject;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import software.amazon.awssdk.services.sagemaker.model.EnvironmentConfig;
import utilities.ExcelConfig;
import utilities.PropertiesConfig;


public class Level_29_EnvironmentFactory extends BaseTest {
    EnvironmentConfig serverConfig;

    @Parameters({"server","environment", "osName","osVersion","browserName","browserVersion","ipAddress","portNumber"})
    @BeforeClass
    public void beforeClass(String serverName,@Optional("BrowserStack") String environment,@Optional("Windows") String osName, String osVersion, @Optional("chrome") String browserName,@Optional("latest") String browserVersion,@Optional("localhost") String ipAddress,@Optional("4444") String portNumber) {
        ConfigFactory.setProperty("environment",serverName);
        serverConfig = ConfigFactory.create(EnvironmentConfig.class);

        driver = getBrowserDirver(environment,serverConfig.getAppUrl(),osName,osVersion,browserName,browserVersion,ipAddress,portNumber);

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