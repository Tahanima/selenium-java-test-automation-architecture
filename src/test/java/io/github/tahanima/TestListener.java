package io.github.tahanima;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.tahanima.report.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * This class takes care of test report generation.
 *
 * @author tahanima
 * @since 02/03/2022
 */
public final class TestListener implements ITestListener {
    private static final ExtentReports REPORT = ReportManager.createReport();

    @Override
    public void onTestSuccess(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testData = result.getParameters()[0].toString();

        ExtentTest test = REPORT.createTest(method.getMethodName())
                .assignCategory(method.getRealClass().getSimpleName())
                .pass(String.format("Test Data: %s%n", testData));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testData = result.getParameters()[0].toString();

        ExtentTest test = REPORT.createTest(method.getMethodName())
                .assignCategory(method.getRealClass().getSimpleName())
                .fail(String.format("Test Data: %s%n", testData))
                .fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                String.format("%s%s.png",
                                        configuration().baseScreenshotPath(),
                                        method.getMethodName())
                        ).build());
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testData = result.getParameters()[0].toString();

        ExtentTest test = REPORT.createTest(method.getMethodName())
                .assignCategory(method.getRealClass().getSimpleName())
                .skip(String.format("Test Data: %s%n", testData));
    }

    @Override
    public void onFinish(final ITestContext context) {
        REPORT.flush();
    }
}
