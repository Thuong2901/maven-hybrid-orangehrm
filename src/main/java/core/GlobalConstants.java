package core;
import java.io.File;


public class GlobalConstants {
    //System info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //App info user
    public static final String DEV_USER_URL = "http://localhost:88/opencart/upload/";

    //App info admin
    public static final String DEV_ADMIN_URL = "http://localhost:88/opencart/upload/adm";

    public static final String ADMIN_USERNAME = "automation";
    public static final String ADMIN_PASSWORD = "Auto123$$##";



    //wait info
    public static final int SHORT_TIME = 10;
    public static final int LONG_TIME = 30;

    //download/upload
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;

    //retry case failed
    public static final int RETRY_NUMBER = 3;

    //brower log/extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

    // html report folder
    public static final String REPORTING_PATH = PROJECT_PATH + File.separator + "htmlReportNG" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

    // Data test/environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator + "dataFile" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;

    public static final long LONG_TIMEOUT = 2;
    public static final String JAVA_VERSION = "";
    public static final String RELATIVE_PROJECT_PATH = "";

    public static final String JIRA_SITE_URL = "https://home.atlassian.com/";
    public static final String JIRA_USERNAME = "thuong29011997@gmail.com";
    public static final String JIRA_PROJECT_KEY = "FRAMEWORK";

    //BrowserStack
    public static final String BROWSER_STACK_USERNAME = "thuong_0TkWi6";
    public static final String BROWSER_STACK_AUTOMATE_KEY ="zizN7x9AqPtUtfdgzDU7";
    public static final String BROWSER_STACK_URL = "https" + BROWSER_STACK_USERNAME +":" +BROWSER_STACK_AUTOMATE_KEY +"@hub-cloud.browserstack.com/wd/hub";

    //SauceLab
    public static final String SAUCE_USERNAME = "automationfc";
    public static final String SAUCE_AUTOMATE_KEY = "f8117ac5-9793-4f8d-89ca-6a3c1d7216a5";
    public static final String SAU_DATA_CENTER_POINT= "eu-central-1";
    public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY
                    + "@ondemand."+ SAU_DATA_CENTER_POINT+ ".saucelabs.com:443/wd/hub";

    //Bitbar
    public static final String BITBAR_AUTOMATE_KEY ="2uj8qEGhFy28N2MmNbWVyoMUBT2A2iQe";
    public static final String BITBAR_EU_URL = "https://eu-desktop-hub.bitbar/com/wd/hub";
    public static final String BITBAR_US_URL = "https://us-desktop-hub.bitbar/com/wd/hub";

    //Lambda
    public static final String LAMBDA_USERNAME = "thuongpham";
    public static final String LAMBDA_AUTOMATE_KEY ="ESx4ZrEDil4nnp8lws97fhFDge2zTKWhXyhsCAYbxrsDg1WOfjQ";
    public static final String LAMBDA_URL = "https://hub.lambdatest.com/wd/hub";

}
