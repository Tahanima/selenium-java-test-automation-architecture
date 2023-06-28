package io.github.tahanima.e2e;

import static io.github.tahanima.util.DataProviderUtil.processCsv;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.tahanima.data.ProductsData;
import io.github.tahanima.util.TestRetry;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author tahanima
 */
public final class ProductsE2ETest extends BaseE2ETest {

    private static final String FILE_PATH = "products.csv";

    @DataProvider(name = "productsData")
    public Object[][] getProductsData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(ProductsData.class, getTestDataFilePath(FILE_PATH), testCaseId);
    }

    @AfterMethod(alwaysRun = true)
    public void captureScreenshotOnFailure(ITestResult result) {
        ITestNGMethod method = result.getMethod();

        if (ITestResult.FAILURE == result.getStatus()) {
            loginPage.captureScreenshot(
                    String.format(
                            "%s_%s_%s",
                            method.getRealClass().getSimpleName(),
                            method.getMethodName(),
                            method.getParameterInvocationCount()));
        }
    }

    @Test(
            testName = "TC-1",
            dataProvider = "productsData",
            groups = {"smoke", "regression"},
            retryAnalyzer = TestRetry.class)
    public void testSuccessfulLogout(final ProductsData data) {
        loginPage.loginAs(data.getUserName(), data.getPassword()).clickOnLogout();

        assertThat(loginPage.getUrl()).isEqualTo(data.getUrl());
    }
}
