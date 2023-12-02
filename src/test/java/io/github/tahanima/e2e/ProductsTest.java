package io.github.tahanima.e2e;

import static io.github.tahanima.util.DataProviderUtil.processTestData;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.tahanima.dto.ProductsDto;
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
public final class ProductsTest extends BaseTest {

    private static final String FILE_PATH = "products.json";

    @DataProvider(name = "productsData")
    public Object[][] getProductsData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processTestData(ProductsDto.class, getTestDataFilePath(FILE_PATH), testCaseId);
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
    public void testSuccessfulLogout(final ProductsDto data) {
        loginPage.loginAs(data.getUsername(), data.getPassword()).clickOnLogout();

        assertThat(loginPage.getUrl()).isEqualTo(data.getUrl());
    }
}
