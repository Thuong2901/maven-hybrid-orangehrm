package com.opencart;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v140.page.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;


public class Level_09_Switch_Url extends BaseTest {


    @Parameters({"userUrl","adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String userUrl,String adminUrl) {
        this.userURL = userUrl;
        this.adminURL =adminUrl;

        adminUser="automationfc";
        adminPassword ="Auto222$$$";
        userFirstname ="Jonh";
        userLastname= "Terry";
        userEmailAddress="john.terry"+getRandomNumber()+"@gmail.com";
        userPassword="";

        driver = getBrowserDriver(browser, userUrl);

        userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
        
    }

    @Test
    public void OpenCart_01_Logging_And_Logout() {

        userHomePage.clickToMyAccount();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage =PageGenerator.getPage(AdminLoginPO.class,driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();

        adminLoginPage = adminCustomerPage.ClickToLogoutLinkAtAdminSite(driver);

        userHomePage = adminLoginPage.openUserSite(driver,userURL);

        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPO = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

    }

    @Test
    public void OpenCart_02_Logging_Without_Logout(){

        userHomePage.clickToMyAccount();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //user-> admin
        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage =PageGenerator.getPage(AdminLoginPO.class,driver);

                //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();

        //admin -> user
        userHomePage = adminLoginPage.openUserSite(driver,userURL);

        userHomePage.clickToMyAccount();
        userMyAccountPO =PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user-> admin
        userMyAccountPO.openAdminSite(driver,adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class,driver);

        //verify hien thi
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

    }

    @Test
    public void OpenCart_03_Multiple_Tab(){
        userHomePage.clickToMyAccount();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        userRegisterPage.openUrlByNewTAB(driver,adminURL);

        //user-> admin
        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage =PageGenerator.getPage(AdminLoginPO.class,driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();
        adminWindowID =adminCustomerPage.getCurrentWindowID(driver);

        //admin -> user
        adminCustomerPage.switchToWindowByID(driver,userWindowID);
        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class,driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);
        userHomePage.clickToMyAccount();
        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,driver);
        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user-> admin
        userMyAccountPO.switchToWindowByID(driver,userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class,driver);


        //verify hien thi
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    private WebDriver driver;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private String adminUser,adminPassword;
    private UserMyAccountPO userMyAccountPO;
    private String userURL,adminURL;
    private String userWindowID,adminWindowID;
    private String userFirstname,userLastname,userEmailAddress,userPassword;


}