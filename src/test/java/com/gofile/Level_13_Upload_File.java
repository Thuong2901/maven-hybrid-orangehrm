package com.gofile;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.gofile.HomePageObject;


public class Level_13_Upload_File extends BaseTest {

    @Parameters({"Url", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String Url) {
        driver = getBrowserDriver(browser, Url);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);

    }



    @Test
    public void Upload_01_Multiple() {
        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.uploadMultipleFiles(driver, catFileName, elephantFileName, flowerFileName);

        Assert.assertTrue(homePage.isProgressBarIconDisappear());

        String successUrl = homePage.getSuccessLink();

        homePage.openPageUrl(driver, successUrl);

        Assert.assertTrue(homePage.isFileUploadedSuccess(catFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(elephantFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(flowerFileName));


    }


    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private pageObjects.gofile.HomePageObject homePage;
    String catFileName = "cat.jpg";
    String elephantFileName = "elephant.jpg";
    String flowerFileName = "flower.jpg";
    String[] fileName = {catFileName, elephantFileName, flowerFileName};

}