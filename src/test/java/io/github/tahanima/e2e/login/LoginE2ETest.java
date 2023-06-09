package io.github.tahanima.e2e.login;

import static io.github.tahanima.util.DataProviderUtils.processCsv;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.tahanima.data.login.LoginData;
import io.github.tahanima.e2e.BaseE2ETest;
import io.github.tahanima.page.login.LoginPage;
import io.github.tahanima.page.product.ProductsPage;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author tahanima
 */
public class LoginE2ETest extends BaseE2ETest {
    private static final String FILE_PATH = "login/login.csv";
    private LoginPage loginPage;

    @DataProvider(name = "loginData")
    public Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginData.class, getTestDataFilePath(FILE_PATH), testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
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
            groups = {"smoke", "regression"})
    public void testCorrectUserNameAndCorrectPassword(final LoginData data) {
        loginPage
                .open()
                .typeUsername(data.getUserName())
                .typePassword(data.getPassword())
                .clickOnLogin();

        ProductsPage productsPage = createInstance(ProductsPage.class);

        assertThat(productsPage.getTitle()).isEqualTo("Products");
    }

    @Test(
            testName = "TC-2",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testIncorrectUserNameAndCorrectPassword(final LoginData data) {
        loginPage
                .open()
                .typeUsername(data.getUserName())
                .typePassword(data.getPassword())
                .clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }

    @Test(
            testName = "TC-3",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testCorrectUserNameAndIncorrectPassword(final LoginData data) {
        loginPage
                .open()
                .typeUsername(data.getUserName())
                .typePassword(data.getPassword())
                .clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }

    @Test(
            testName = "TC-4",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testIncorrectUserNameAndIncorrectPassword(final LoginData data) {
        loginPage
                .open()
                .typeUsername(data.getUserName())
                .typePassword(data.getPassword())
                .clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }

    @Test(
            testName = "TC-5",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testBlankUserName(final LoginData data) {
        loginPage.open().typePassword(data.getPassword()).clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }

    @Test(
            testName = "TC-6",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testBlankPassword(final LoginData data) {
        loginPage.open().typeUsername(data.getUserName()).clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }

    @Test(
            testName = "TC-7",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testLockedOutUser(final LoginData data) {
        loginPage
                .open()
                .typeUsername(data.getUserName())
                .typePassword(data.getPassword())
                .clickOnLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(data.getErrorMessage());
    }
}
