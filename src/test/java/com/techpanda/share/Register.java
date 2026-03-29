package com.techpanda.share;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Register extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeTest
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        homepage = PageGenerator.getPage(HomePO.class,driver);
        loginPage = homepage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccountLink();

        registerPage.enterFirstName("Automation");
        registerPage.enterLastName("FC");
        registerPage.enterToEmail("afc" + getRandomNumber() +"gmail.com");
        registerPage.enterToPassword("123456a@");
        registerPage.enterToConfirmPassword("123456a");
        registerPage.enterToRegisterButton();
        //myAccountPage = registerPage.acceptContinueAlert();
        myAccountPage= PageGenerator.getPage(MyAccountPO.class,driver);


        verifyEquals(myAccountPage.getSuccessMessage(),"Thank you for registering with Main Website Store.");

        cookies = myAccountPage.getPageCookies(driver);

        closeBrowser();

    }

    private WebDriver driver;
    private HomePO homepage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private RegisterPO registerPage;
    public static Set<Cookie> cookies;

}