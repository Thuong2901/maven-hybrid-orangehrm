package ui.orangehrm.user;

//import từ thư viện
import core.BasePage;
import javaSDET.Topic_01_KeyWords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//import từ class/interface từ package khác


public class Login_02_BasePage_III_Extend extends BasePage {
    private WebDriver driver;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private Topic_01_KeyWords topic_01;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void Login_01_Empty(){
        openPageUrl(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","");
        sendKeyToElement(driver,"//input[@type='password']","");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText
                (driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(getElementText
                (driver,"//input[@type='password']/parent::div/following-sibling::span"),"Required");
    }

    @Test
    public void Login_02_Invalid_Username(){
        openPageUrl(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","1234");
        sendKeyToElement(driver,"//input[@type='password']","admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText
                (driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),"Invalid credentials");

    }

    @Test
    public void Login_03_Invalid_Password(){
        openPageUrl(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","Admin");
        sendKeyToElement(driver,"//input[@type='password']","admin1234");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]")).getText(),"Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password(){
        openPageUrl(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","Admin");
        sendKeyToElement(driver,"//input[@type='password']","admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSprinnerInvisible());

        Assert.assertEquals(getElementText(driver,"//div[@class='oxd-topbar-header-title']//h6"),"Dashboard");

    }

    public boolean isAllLoadingSprinnerInvisible(){
        return waitElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
