package io.github.tahanima.e2e;

import static io.github.tahanima.config.ConfigurationManager.config;

import io.github.tahanima.factory.BasePageFactory;
import io.github.tahanima.factory.BrowserFactory;
import io.github.tahanima.ui.page.LoginPage;
import io.github.tahanima.util.TestListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * @author tahanima
 */
@Listeners(TestListener.class)
public abstract class BaseE2ETest {

    private final WebDriver driver =
            BrowserFactory.valueOf(config().browser().toUpperCase()).getDriver();
    protected LoginPage loginPage;

    protected String getTestDataFilePath(String path) {
        return config().baseTestDataPath() + path;
    }

    protected String getScreenshotFilePath(String path) {
        return config().baseScreenshotPath() + path;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = BasePageFactory.createInstance(driver, LoginPage.class);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
