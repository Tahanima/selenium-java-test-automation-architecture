package io.github.tahanima.login;

import io.github.tahanima.BaseTest;
import io.github.tahanima.data.login.LoginData;
import io.github.tahanima.page.login.LoginPage;
import io.github.tahanima.page.product.ProductsPage;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static io.github.tahanima.util.DataProviderUtil.processCsv;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author tahanima
 */
public class LoginTest extends BaseTest {
    private static final String FILE_PATH = "login/login.csv";
    private LoginPage loginPage;

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginData.class, FILE_PATH, testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        ITestNGMethod method = result.getMethod();

        if (ITestResult.FAILURE == result.getStatus()) {
            loginPage.captureScreenshot(method.getMethodName());
        }
    }

    @Test(testName = "TC-1", dataProvider = "loginData")
    public void testCorrectUserNameAndCorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        ProductsPage productsPage = createInstance(ProductsPage.class);

        assertThat(productsPage.getTitle()).isEqualTo("PRODUCTS");
    }

    @Test(testName = "TC-2", dataProvider = "loginData")
    public void testIncorrectUserNameAndCorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-3", dataProvider = "loginData")
    public void testCorrectUserNameAndIncorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-4", dataProvider = "loginData")
    public void testIncorrectUserNameAndIncorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-5", dataProvider = "loginData")
    public void testBlankUserName(final LoginData loginDto) {
        loginPage.goTo().enterPassword(loginDto.getPassword()).clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-6", dataProvider = "loginData")
    public void testBlankPassword(final LoginData loginDto) {
        loginPage.goTo().enterUsername(loginDto.getUserName()).clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-7", dataProvider = "loginData")
    public void testLockedOutUser(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }
}
