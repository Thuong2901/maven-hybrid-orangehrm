package ui.orangehrm.user;

//import từ thư viện
import core.BasePage;
import javaSDET.Topic_01_KeyWords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//import từ class/interface từ package khác


public class Login_02_BasePage_I_Initial {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private Topic_01_KeyWords topic_01;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        basePage = new BasePage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void Login_01_Empty(){
        basePage.openPageUrl(driver,appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","");
        basePage.sendkeyToElement(driver,"//input[@type='password']","");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText
                (driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(basePage.getElementText
                (driver,"//input[@type='password']/parent::div/following-sibling::span"),"Required");
    }

    @Test
    public void Login_02_Invalid_Username(){
        basePage.openPageUrl(driver,appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","1234");
        basePage.sendkeyToElement(driver,"//input[@type='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText
                (driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),"Invalid credentials");

    }

    @Test
    public void Login_03_Invalid_Password(){
        basePage.openPageUrl(driver,appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","Admin");
        basePage.sendkeyToElement(driver,"//input[@type='password']","admin1234");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]")).getText(),"Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password(){
        basePage.openPageUrl(driver,appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","Admin");
        basePage.sendkeyToElement(driver,"//input[@type='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='oxd-topbar-header-title']//h6"),"Dashboard");

    }

    public boolean isAllLoadingSpinnerInvisible(){
        return basePage.waitElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
