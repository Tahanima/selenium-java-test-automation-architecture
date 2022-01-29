package io.github.tahanima.regression.login;

import static io.github.tahanima.util.DataProviderUtil.processCsv;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.tahanima.BaseTest;
import io.github.tahanima.dto.login.LoginDto;
import io.github.tahanima.page.login.LoginPage;
import io.github.tahanima.page.product.ProductsPage;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author tahanima
 * @since 01/29/2022
 */
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private static final String FILE_PATH = "regression/login/login.csv";

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
    }

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginDto.class, FILE_PATH, testCaseId);
    }

    @Test(testName = "TC-1", dataProvider = "loginData")
    public void testCorrectUserNameAndCorrectPassword(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        ProductsPage productsPage = createInstance(ProductsPage.class);

        assertThat(productsPage.getTitle()).isEqualTo("PRODUCTS");
    }

    @Test(testName = "TC-2", dataProvider = "loginData")
    public void testIncorrectUserNameAndCorrectPassword(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-3", dataProvider = "loginData")
    public void testCorrectUserNameAndIncorrectPassword(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-4", dataProvider = "loginData")
    public void testIncorrectUserNameAndIncorrectPassword(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-5", dataProvider = "loginData")
    public void testBlankUserName(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-6", dataProvider = "loginData")
    public void testBlankPassword(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(testName = "TC-7", dataProvider = "loginData")
    public void testLockedOutUser(final LoginDto loginDto) {
        loginPage.goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }
}
