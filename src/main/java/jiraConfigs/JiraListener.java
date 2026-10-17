
package jiraConfigs;

import core.GlobalConstants;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class JiraListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Method method = result.getMethod()
                .getConstructorOrMethod()
                .getMethod();

        JiraCreateIssue annotation =
                method.getAnnotation(JiraCreateIssue.class);

        // Không tạo issue nếu method không có annotation
        // hoặc annotation không bật create issue.
        if (annotation == null || !annotation.isCreateIssue()) {
            return;
        }

        String jiraApiKey = GlobalConstants.JIRA_API_KEY;

        if (jiraApiKey == null || jiraApiKey.trim().isEmpty()) {
            System.err.println(
                    "JIRA_API_KEY chưa được cấu hình. Bỏ qua tạo Jira issue."
            );
            return;
        }

        JiraServiceProvider jiraServiceProvider =
                new JiraServiceProvider(
                        GlobalConstants.JIRA_SITE_URL,
                        GlobalConstants.JIRA_USERNAME,
                        jiraApiKey,
                        GlobalConstants.JIRA_PROJECT_KEY
                );

        String issueDescription =
                "Failure Reason from Run Regression Automation Testing\n\n"
                        + result.getThrowable().getMessage()
                        + "\n\n"
                        + ExceptionUtils.getFullStackTrace(
                        result.getThrowable()
                );

        String issueSummary =
                method.getName() + " Failed in Automation Testing";

        jiraServiceProvider.createJiraIssue(
                "Bug",
                issueSummary,
                issueDescription
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
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
