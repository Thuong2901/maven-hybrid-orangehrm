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


public class Level_13_Upload_File extends BaseTest {

    @Parameters({"Url", "browser"})
    @BeforeClass
    public void beforeClass(String browser, String Url) {
        driver = getBrowserDriver(browser, Url);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);

    }


    @Test
    public void Upload_01_Single() {
        homePage.uploadMultipleFiles(driver,catFileName);
        homePage.uploadMultipleFiles(driver,elephantFileName);
        homePage.uploadMultipleFiles(driver,flowerFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(catFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(elephantFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(flowerFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(catFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(elephantFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(flowerFileName));
    }

    @Test
    public void Upload_02_Multiple() {
        homePage.refreshToPage(driver);

        homePage.uploadMultipleFiles(driver,catFileName,elephantFileName,flowerFileName);
        //homePage.uploadMultipleFiles(driver,fileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(catFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(elephantFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(flowerFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(catFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(elephantFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(flowerFileName));
    }


    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String catFileName = "cat.jpg";
    String elephantFileName = "elephant.jpg";
    String flowerFileName = "flower.jpg";
    String[] fileName = {catFileName, elephantFileName, flowerFileName};

}