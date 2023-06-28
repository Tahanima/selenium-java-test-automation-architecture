package io.github.tahanima.e2e;

import static io.github.tahanima.util.DataProviderUtil.processCsv;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.tahanima.data.LoginData;
import io.github.tahanima.ui.page.ProductsPage;
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
public final class LoginE2ETest extends BaseE2ETest {

    private static final String FILE_PATH = "login.csv";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginData.class, getTestDataFilePath(FILE_PATH), testCaseId);
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
            dataProvider = "loginData",
            groups = {"smoke", "regression"},
            retryAnalyzer = TestRetry.class)
    public void testCorrectUserNameAndCorrectPassword(final LoginData data) {
        ProductsPage productsPage = loginPage.loginAs(data.getUsername(), data.getPassword());

        assertThat(productsPage.getTitle()).isEqualTo("Products");
    }

    @Test(
            testName = "TC-2",
            dataProvider = "loginData",
            groups = {"validation", "regression"},
            retryAnalyzer = TestRetry.class)
    public void testImproperCredentialsShouldGiveErrorMessage(final LoginData data) {
        loginPage.loginAs(data.getUsername(), data.getPassword());

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }
}
