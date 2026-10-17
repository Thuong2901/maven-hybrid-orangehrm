package reportConfig;

import core.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureTestListener implements ITestListener {

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Text attachment of {0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();

        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            saveScreenshotPNG(result.getName(), driver);
            saveTextLog(
                    getTestMethodName(result)
                            + " failed and screenshot taken!"
            );
        }
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }
}
