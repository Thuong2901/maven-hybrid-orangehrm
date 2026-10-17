
package com.orangeHRM;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.PageGenerator;
import pageObjects.orangeHRM.LoginPageObject;
import utilities.PropertiesConfig;

public class Level_29_EnvironmentFactory extends BaseTest {

    @Parameters({
            "server",
            "environment",
            "osName",
            "osVersion",
            "browserName",
            "browserVersion",
            "ipAddress",
            "portNumber"
    })
    @BeforeClass
    public void beforeClass(
            String serverName,
            @Optional("BrowserStack") String environment,
            @Optional("Windows") String osName,
            @Optional("latest") String osVersion,
            @Optional("chrome") String browserName,
            @Optional("latest") String browserVersion,
            @Optional("localhost") String ipAddress,
            @Optional("4444") String portNumber) {

        PropertiesConfig propertiesConfig =
                PropertiesConfig.getProperties(serverName);

        String appUrl = propertiesConfig.getApplicationUrl();

        // Tạm thời dùng driver thông thường để tránh gọi sai method.
        // Sẽ chọn driver phù hợp sau khi kiểm tra BaseTest.
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = PageGenerator.getPage(
                LoginPageObject.class, driver
        );
    }

    @Test
    public void Employee_01_NewEmployee() {
        // Thêm các bước test tại đây.
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
}
