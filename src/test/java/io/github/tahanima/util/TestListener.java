package io.github.tahanima.util;

import static io.github.tahanima.config.ConfigurationManager.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.tahanima.dto.BaseDto;
import io.github.tahanima.report.ExtentReportManager;

import org.apache.commons.text.StringEscapeUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * @author tahanima
 */
public class TestListener implements ITestListener {

    private static final ExtentReports REPORT = ExtentReportManager.createReport();

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testCaseId = "#";
        String testCaseDescription = "";
        String testData = "No data parameters are found.";

        if (result.getParameters().length > 0) {
            BaseDto data = (BaseDto) result.getParameters()[0];
            testCaseId = data.getTestCaseId();
            testCaseDescription = data.getTestCaseDescription();
            testData = StringEscapeUtils.escapeHtml4(data.toString());
        }

        REPORT.createTest(String.format("[%s] %s", testCaseId, method.getMethodName()))
                .assignCategory(method.getRealClass().getSimpleName())
                .pass(
                        String.format(
                                "<b>Test Case Description:</b> %s<br><br><b>Test Data:</b> %s<br><br><b>Test Execution Time:</b> <i>%.3f</i> seconds",
                                testCaseDescription,
                                testData,
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testCaseId = "#";
        String testCaseDescription = "";
        String testData = "No data parameters are found.";

        if (result.getParameters().length > 0) {
            BaseDto data = (BaseDto) result.getParameters()[0];
            testCaseId = data.getTestCaseId();
            testCaseDescription = data.getTestCaseDescription();
            testData = StringEscapeUtils.escapeHtml4(data.toString());
        }

        REPORT.createTest(String.format("[%s] %s", testCaseId, method.getMethodName()))
                .assignCategory(method.getRealClass().getSimpleName())
                .fail(
                        String.format(
                                "<b>Test Case Description:</b> %s<br><br><b>Test Data:</b> %s<br><br><b>Test Execution Time:</b> <i>%.3f</i> seconds",
                                testCaseDescription,
                                testData,
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0))
                .fail(
                        result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                        String.format(
                                                "../../%s%s_%s_%s.png",
                                                config().baseScreenshotPath(),
                                                method.getRealClass().getSimpleName(),
                                                method.getMethodName(),
                                                method.getParameterInvocationCount()))
                                .build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testCaseId = "#";
        String testCaseDescription = "";
        String testData = "No data parameters are found.";

        if (result.getParameters().length > 0) {
            BaseDto data = (BaseDto) result.getParameters()[0];
            testCaseId = data.getTestCaseId();
            testCaseDescription = data.getTestCaseDescription();
            testData = StringEscapeUtils.escapeHtml4(data.toString());
        }

        REPORT.createTest(String.format("[%s] %s", testCaseId, method.getMethodName()))
                .assignCategory(method.getRealClass().getSimpleName())
                .skip(
                        String.format(
                                "<b>Test Case Description:</b> %s<br><br><b>Test Data:</b> %s<br><br><b>Test Execution Time:</b> <i>%.3f</i> seconds",
                                testCaseDescription,
                                testData,
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0));
    }

    @Override
    public void onFinish(ITestContext context) {
        REPORT.flush();
    }
}
