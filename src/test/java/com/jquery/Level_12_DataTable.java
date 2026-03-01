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
    public void Table_02_Search() {
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

    public void Table_03_Action(){
        homePage.enterToHeaderTextboxByName("Country","Seychelles");
        homePage.sleepInSecond(2);

        homePage.clickToActionByCountryName("Seychelles","remove");
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Country","Armenia");
        homePage.sleepInSecond(2);
        homePage.clickToActionByCountryName("Armenia","edit");

    }

    public void Table_04_Index() {
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","3","Putin");
        homePage.enterToTextboxByColumnNameAndRowIndex("Company","3","Russia");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","3","12");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","3","Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","3");
        homePage.actionToRowByRowIndex("3","Move Up");
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","6","Trump");
        homePage.enterToTextboxByColumnNameAndRowIndex("Company","6","USA");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","6","19");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","6","United States");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","6");
        homePage.actionToRowByRowIndex("6","Insert");
        homePage.sleepInSecond(3);
    }

    @Test
    public void Table_05_Get_All_Value() {
        List<String> columnActualValue =  homePage.getColumnAllValueByColumnName("Country");
        System.out.println(columnActualValue.size());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;

}