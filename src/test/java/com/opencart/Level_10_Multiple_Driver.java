package com.opencart;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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


public class Level_10_Multiple_Driver extends BaseTest {


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

        userDriver = getBrowserDriver(browser, userUrl);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        adminDriver = getBrowserDriver(adminUrl, browser);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,adminDriver);
    }


    @Test
    public void OpenCart_02_Logging_Without_Logout(){

        userHomePage.clickToMyAccount();
        userLoginPage =PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickToContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());


                //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.OpenCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

        userHomePage.clickToMyAccount();
        userMyAccountPO =PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

    }


    @AfterClass
    public void afterClass() {
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    private WebDriver userDriver;
    private WebDriver adminDriver;
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