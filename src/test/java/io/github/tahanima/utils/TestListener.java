package io.github.tahanima.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.tahanima.data.BaseData;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import static io.github.tahanima.config.ConfigurationManager.configuration;

/**
 * This class takes care of test report generation.
 *
 * @author tahanima
 */
public final class TestListener implements ITestListener {
    private static final ExtentReports REPORT = ExtentReportManager.createReport();

    @Override
    public void onTestSuccess(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        BaseData baseData = (BaseData) result.getParameters()[0];
        String testCaseId = baseData.getTestCaseId();
        String testCaseDescription = baseData.getTestCaseDescription();
        String testData = baseData.toString();

        REPORT.createTest(String.format("[%s] %s", testCaseId, testCaseDescription))
                .assignCategory(method.getRealClass().getSimpleName())
                .pass(
                        String.format(
                                "<b>Test Execution Time:</b> <i>%.2f</i> seconds",
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0))
                .pass(String.format("<b>Test Data:</b> %s", testData));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        BaseData baseData = (BaseData) result.getParameters()[0];
        String testCaseId = baseData.getTestCaseId();
        String testCaseDescription = baseData.getTestCaseDescription();
        String testData = baseData.toString();

        REPORT.createTest(String.format("[%s] %s", testCaseId, testCaseDescription))
                .assignCategory(method.getRealClass().getSimpleName())
                .fail(
                        String.format(
                                "<b>Test Execution Time:</b> <i>%.2f</i> seconds",
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0))
                .fail(String.format("<b>Test Data:</b> %s", testData))
                .fail(
                        result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                        String.format(
                                                "%s%s.png",
                                                configuration().baseScreenshotPath(),
                                                method.getMethodName()))
                                .build());
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        BaseData baseData = (BaseData) result.getParameters()[0];
        String testCaseId = baseData.getTestCaseId();
        String testCaseDescription = baseData.getTestCaseDescription();
        String testData = baseData.toString();

        REPORT.createTest(String.format("[%s] %s", testCaseId, testCaseDescription))
                .assignCategory(method.getRealClass().getSimpleName())
                .skip(
                        String.format(
                                "<b>Test Execution Time:</b> <i>%.2f</i> seconds",
                                (double) (result.getEndMillis() - result.getStartMillis())
                                        / 1000.0))
                .skip(String.format("<b>Test Data:</b> %s", testData));
    }

    @Override
    public void onFinish(final ITestContext context) {
        REPORT.flush();
    }
}
