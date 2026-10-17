
package com.orangeHRM;

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

/*public class Level_28_Cloud_BrowserStack extends BaseTest {

    private static final Logger log =
            LoggerFactory.getLogger(Level_28_Cloud_BrowserStack.class);

    @Parameters({"environment", "osName", "osVersion", "browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(
            String environment,
            String osName,
            String osVersion,
            String browserName,
            String browserVersion) {

        propertiesConfig = PropertiesConfig.getProperties(environment);

        driver = getBrowserDriverBrowserStack(
                propertiesConfig.getApplicationUrl(),
                osName,
                osVersion,
                browserName,
                browserVersion
        );

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
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
    private String employeeID, adminUserName, adminPassword,
            employeeUsername, employeeEmail;
}*/
