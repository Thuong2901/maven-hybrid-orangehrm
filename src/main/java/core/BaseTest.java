package core;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.logging.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import reportConfig.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static core.BrowserList.*;

public class BaseTest {
    private WebDriver driver;


    protected WebDriver getBrowserDriver( String appUrl,String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        Path path=  null;
        File extensionFilePath= null;
        switch (browser){
            case FIREFOX:
                FirefoxDriverService fService= new GeckoDriverService.Builder().withLogOutput(System.out).
                        withLogFile(new File (GlobalConstants.BROWSER_LOG_PATH+ "FireFoxLog.log")).build();
                driver =new FirefoxDriver(fService);
                break;
            case CHROME:
                ChromeDriverService cService= new ChromeDriverService.Builder().withLogOutput(System.out).
                        withLogFile(new File (GlobalConstants.BROWSER_LOG_PATH+ "ChromeLog.log")).build();
                driver = new ChromeDriver(cService);
                break;
            case EDGE:

                driver = new EdgeDriver();
                break;
            case HEAD_CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(chromeOptions);
                break;
            case HEAD_FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case HEAD_EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("window-size=1920x1080");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        driver.get(appUrl);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;
    }

    protected void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    protected void closeBrowser(WebDriver driver) {
        if (!(null == driver)) {
            driver.quit();
        }
    }


    protected int getRandomNumber(){
        return new Random().nextInt(99999);
    }



    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("-----------PASSED----------");
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("-----------FAILED----------");
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("-----------PASSED----------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("-----------FAILED----------");
        }
        return pass;
    }


    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("-----------PASSED----------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("-----------FAILED----------");
        }
        return pass;
    }

    protected boolean verifyNotEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertNotEquals(actual, expected);
            log.info("-----------PASSED----------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("-----------FAILED----------");
        }
        return pass;
    }


    public WebDriver getDriver() {
        return this.driver;
    }

    protected void takeScreenshot(){
        String base64Screenshot="data:immage/png;base64,"+((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtentManager.getTest().log(LogStatus.INFO,"Test Failed",ExtentManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

    protected WebDriver getDriverInstance() {
    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("htmlAllure");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
