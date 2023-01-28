package io.github.tahanima.e2e;

import io.github.tahanima.driver.DriverManager;
import io.github.tahanima.pages.BasePage;
import io.github.tahanima.pages.BasePageFactory;
import io.github.tahanima.utils.TestListener;
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
