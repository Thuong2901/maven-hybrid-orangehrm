package ui.orangehrm.user;

//import từ thư viện
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
import javaSDET.Topic_01_KeyWords;


public class Login_01_DRY {
    private WebDriver driver;
    private Topic_01_KeyWords topic_01;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void Login_01_Empty(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath
                ("//input[@name='username']/parent::div/following-sibling::span")).getText(),"Required");

        Assert.assertEquals(driver.findElement(By.xpath
                ("//input[@type='password']/parent::div/following-sibling::span")).getText(),"Required");
    }

    @Test
    public void Login_02_Invalid_Username(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("1234");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("div.orangehrm-login-error p.oxd-text oxd-text--p oxd-alert-content-text")).getText(),"Invalid credentials");

    }

    @Test
    public void Login_03_Invalid_Password(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("div.orangehrm-login-error p.oxd-text oxd-text--p oxd-alert-content-text")).getText(),"Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertTrue(isAllLoadingSprinnerInvisible());

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("div.oxd-topbar-header-title h6")).getText(),"Dashboard");


    }

    public boolean isAllLoadingSprinnerInvisible(){
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
