package io.github.tahanima.e2e;

import static io.github.tahanima.config.ConfigurationManager.config;

import io.github.tahanima.page.BasePage;
import io.github.tahanima.page.BasePageFactory;
import io.github.tahanima.util.DriverManager;
import io.github.tahanima.util.TestListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * This class provides the basic functionalities required by the test scripts.
 *
 * @author tahanima
 */
@Listeners(TestListener.class)
public abstract class BaseE2ETest {
    private final WebDriver driver = DriverManager.createDriver();

    public abstract void initialize();

    protected String getTestDataFilePath(String path) {
        return config().baseTestDataPath() + path;
    }

    protected String getScreenshotFilePath(String path) {
        return config().baseScreenshotPath() + path;
    }

    protected <T extends BasePage> T createInstance(final Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        initialize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
