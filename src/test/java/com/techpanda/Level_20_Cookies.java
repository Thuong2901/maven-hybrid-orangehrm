package com.techpanda;

//import từ thư viện

import com.techpanda.share.Register;
import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Level_20_Cookies extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String appUrl) {
        driver = getBrowserDriver(browser, appUrl);

        this.cookies = Register.cookies;

        homepage = PageGenerator.getPage(HomePO.class,driver);
        loginPage = homepage.openLoginPage();
        loginPage.setPageCookies(driver,this.cookies);

        myAccountPage= PageGenerator.getPage(MyAccountPO.class,driver);

        verifyEquals(myAccountPage.getMyAccountPageTitle(),"My Dashboard");

    }

    @Test
    public void Employee_01_CreateNewEmployee() {

    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    private WebDriver driver;
    private HomePO homepage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private Set<Cookie> cookies;

}