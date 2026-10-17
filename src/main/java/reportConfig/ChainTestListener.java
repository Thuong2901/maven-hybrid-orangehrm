
package reportConfig;

import java.util.HashMap;
import java.util.Map;

import core.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.chaintest.service.ChainPluginService;

public class ChainTestListener
        extends com.aventstack.chaintest.plugins.ChainTestListener {

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);

        try {
            Map<String, String> sysInfo = new HashMap<>();
            sysInfo.put("Project Name", "Orange HRM");
            sysInfo.put("Execution Environment", "Local");
            sysInfo.put("Test Environment", "QA");
            sysInfo.put("Browser Name", "Chrome");

            ChainPluginService.getInstance().addSystemInfo(sysInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        try {
            super.onTestStart(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            super.onTestSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);

        try {
            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriver();

            if (driver != null) {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                byte[] imageBytes =
                        screenshot.getScreenshotAs(OutputType.BYTES);

                com.aventstack.chaintest.plugins.ChainTestListener
                        .embed(imageBytes, "image/png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            super.onTestSkipped(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {
        try {
            super.onFinish(testContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutionFinish() {
        try {
            super.onExecutionFinish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
