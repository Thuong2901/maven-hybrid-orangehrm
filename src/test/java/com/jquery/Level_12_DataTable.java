package com.jquery;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;



public class Level_12_DataTable extends BaseTest {

    @Parameters({"Url", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String Url) {
        driver = getBrowserDriver(browser, Url);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);

    }

    @Test
    public void Employee_01_Table() {
        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));

        homePage.openPageByNumber("20");
        Assert.assertTrue(homePage.isPageActiveByNumber("20"));

    }

    @Test
    public void Employee_02_Search() {
        homePage.enterToHeaderTextboxByName("Country","Seychelles");

        Assert.assertTrue(homePage.isPageInfoDisplayed("624","Seychelles","651","1270"));

        homePage.refreshToPage(driver);
        homePage.sleepInSecond(2);

        homePage.enterToHeaderTextboxByName("Males","709");
        Assert.assertTrue(homePage.isPageInfoDisplayed("643","US Virgin Islands","709","1350"));
        homePage.refreshToPage(driver);
        homePage.sleepInSecond(2);

        homePage.enterToHeaderTextboxByName("Females","836");
        Assert.assertTrue(homePage.isPageInfoDisplayed("836","Channel Islands","919","1755"));
        homePage.refreshToPage(driver);
        homePage.sleepInSecond(2);

    }


    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;

}