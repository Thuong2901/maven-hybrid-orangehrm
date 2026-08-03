package core;

import browserFactory.*;
import com.relevantcodes.extentreports.LogStatus;
import environmentFactory.BrowserStackEnvironment;
import environmentFactory.EnvironmentList;
import environmentFactory.SaucelabEnvironment;
import org.openqa.selenium.MutableCapabilities;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import reportConfig.ExtentManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

public class BaseTest {
    private WebDriver driver;

    //
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
    public WebDriver getDriver(){
        return threadDriver.get();
    }


    protected WebDriver getBrowserDriver( String appUrl,String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser){
            case FIREFOX:
                threadDriver.set(new FirefoxDriver());
                break;
            case CHROME:
                threadDriver.set(new ChromeDriver());
                break;
            case EDGE:
                threadDriver.set(new EdgeDriver());
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        threadDriver.get().get(GlobalConstants.DEV_USER_URL);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return threadDriver.get();
    }

    protected WebDriver getBrowserDriver( String appUrl,String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        Path path=  null;
        File extensionFilePath= null;
        switch (browser){
            case FIREFOX:
                driver = new FirefoxBrowserMananger().getDriver();
                break;
            case CHROME:
                driver = new ChromeBrowserMananger().getDriver();
                break;
            case EDGE:
                driver = new EdgeBrowserManager().getDriver();
                driver = new EdgeDriver();
                break;
            case HEAD_CHROME:
                driver = new ChromeHeadlessBrowserManager().getDriver();
                break;
            case HEAD_FIREFOX:
                driver = new FirefoxHeadlessBrowserManager().getDriver();
                break;
            case HEAD_EDGE:
                driver = new EdgeHeadlessBrowserManager().getDriver();
                break;
            case SAFARI:
                driver = new SafariBrowserManager().getDriver();
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
    //Cloud:BrowserStack
    protected WebDriver getBrowserDriverBrowserStack(String url, String osName, String osVersion, String browserName, String browserVersion) {

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String,Object> bstackOptions = new HashMap<>();

        capabilities.setCapability("browserName", browserName);
        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("browserVersion", browserVersion);
        bstackOptions.put("userName", GlobalConstants.BROWSER_STACK_USERNAME);
        bstackOptions.put("accessKey", GlobalConstants.BROWSER_STACK_AUTOMATE_KEY);
        bstackOptions.put("seleniumVersion", "4.29.0");
        bstackOptions.put("projectName", "Nopcommerce");
        bstackOptions.put("buildName","Automation");
        capabilities.setCapability("bstack:options",bstackOptions);

        try {
            driver = new RemoteWebDriver(
                    new URL(GlobalConstants.SAUCE_URL),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }

    //Cloud:SauceLab
    protected WebDriver getBrowserDriverSauceLab(String url, String platformName, String browserName, String browserVersion) {

        MutableCapabilities capability = null;
        browserName = browserName.toLowerCase();

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setCapability("platformName", platformName);
                fOptions.setCapability("browserVersion", browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setCapability("platformName", platformName);
                cOptions.setCapability("browserVersion", browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setCapability("platformName", platformName);
                eOptions.setCapability("browserVersion", browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setCapability("platformName", platformName);
                sOptions.setCapability("browserVersion", browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        HashMap<String, String> sauceOptions = new HashMap<String, String>();
        sauceOptions.put("username", GlobalConstants.SAUCE_USERNAME);
        sauceOptions.put("accessKey", GlobalConstants.SAUCE_AUTOMATE_KEY);
        sauceOptions.put("build", "automation-fc-build");
        sauceOptions.put("name", "Run on " + platformName + " | " + browserName + " | " + browserVersion);

        capability.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCE_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }

    //Cloud:Bitbar
    protected WebDriver getBrowserDriverBitbar(String url, String platformName, String platformVersion, String browserName, String browserVersion) {

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);

        HashMap<String, String> bitbarOptions = new HashMap<String, String>();
        bitbarOptions.put("project", "NopCommerce");
        bitbarOptions.put("testrun", "Run on " + platformName + " | " + platformVersion + " | " + browserName + " | " + browserVersion);
        bitbarOptions.put("apiKey", GlobalConstants.BITBAR_AUTOMATE_KEY);
        bitbarOptions.put("osVersion", platformVersion);

        if (platformName.contains("Windows") || platformName.contains("Linux")) {
            bitbarOptions.put("resolution", "1920x1080");
        } else {
            bitbarOptions.put("resolution", "1920x1200");
        }

        bitbarOptions.put("seleniumVersion", "4");

        capabilities.setCapability("bitbar:options", bitbarOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BITBAR_EU_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }

    //Cloud:Lambda
    protected WebDriver getBrowserDriverLambda(String url, String osName, String browserName, String browserVersion) {
        MutableCapabilities capability = null;

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setPlatformName(osName);
                fOptions.setBrowserVersion(browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setPlatformName(osName);
                cOptions.setBrowserVersion(browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setPlatformName(osName);
                eOptions.setBrowserVersion(browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setPlatformName(osName);
                sOptions.setBrowserVersion(browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        HashMap<String, Object> lambdaOptions = new HashMap<String, Object>();
        lambdaOptions.put("username", GlobalConstants.LAMBDA_USERNAME);
        lambdaOptions.put("accessKey", GlobalConstants.LAMBDA_AUTOMATE_KEY);
        lambdaOptions.put("visual", true);
        lambdaOptions.put("video", true);
        lambdaOptions.put("build", "nopcommerce-build");
        lambdaOptions.put("project", "NopCommerce - UI Automation Testing");
        lambdaOptions.put("name", "Run on " + osName + " | " + browserName + " | " + browserVersion + " | " + formater.format(calendar.getTime()));
        lambdaOptions.put("w3c", true);
        lambdaOptions.put("selenium_version", "4.29.0");
        lambdaOptions.put("resolution", "1920x1080");
        lambdaOptions.put("plugin", "java-testNG");

        capability.setCapability("LT:Options", lambdaOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.LAMBDA_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    //CLOUD: DeviceFarm
    protected WebDriver getBrowserDriverDeviceFarm(String appURL, String platformName, String browserName, String browserVersion) {
        MutableCapabilities capability = null;
        browserName = browserName.toLowerCase();

        switch (browserName.toLowerCase()) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setPlatformName(platformName);
                fOptions.setBrowserVersion(browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setPlatformName(platformName);
                cOptions.setBrowserVersion(browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setPlatformName(platformName);
                eOptions.setBrowserVersion(browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setPlatformName(platformName);
                sOptions.setBrowserVersion(browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        DeviceFarmClient client= DeviceFarmClient.builder().region(Region.US_WEST_2).build();
        CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300).projectArn(GlobalConstants.AWS_DEVICE_FARM).build();
        URL testGridUrl = null;

        try {
            CreateTestGridUrlResponse response = client.createTestGridUrl(request);
            testGridUrl = new URL(response.url());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new RemoteWebDriver(testGridUrl,capability);

        driver.get(appURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIME));
        return driver;
    }

    //All
   protected WebDriver getBrowserDirver(String environmentName,String url,String osName,String osVersion,String browserName,String browserVersion,String ipAddress,String portNumber){
       EnvironmentList environmentList = EnvironmentList.valueOf(environmentName.toUpperCase());

       switch (environmentList){

           case BROWSERSTACK:
               driver = new BrowserStackEnvironment(osName,osVersion,ipAddress,portNumber).createDirver();
               break;
           case SAUCELAB:
               driver = new SaucelabEnvironment(osName,browserName,browserVersion).createDirver();
               break;
           case BITBAR:
               driver = new FirefoxHeadlessBrowserManager().getDriver();
               break;
           case LAMBDA:
               driver = new EdgeHeadlessBrowserManager().getDriver();
               break;
           case DEVICEFARM:
               driver = new SafariBrowserManager().getDriver();
           default:
               throw new RuntimeException("Environment name is not valid!");
       }

       driver.get(appUrl);
       //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       driver.manage().window().maximize();
       return driver;
        return null;
   }

    private String getEnvironmentUrl(String environmentName){
        String envUrl= null;
        switch (environmentName){
            case "Dev":
                envUrl ="https://opensource-demo.orangehrmlive.com/";
                break;
            case "Test":
                envUrl ="https://test.orangehrmlive.com/";
                break;
            case "Staging":
                envUrl ="https://staging.orangehrmlive.com/";
                break;
            case "Prod":
                envUrl ="https://orangehrmlive.com/";
                break;
        }
         return envUrl;
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
