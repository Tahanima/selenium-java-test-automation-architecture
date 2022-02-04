package io.github.tahanima;

import io.github.tahanima.driver.DriverManager;
import io.github.tahanima.page.BasePage;
import io.github.tahanima.page.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * This class provides the basic functionalities
 * required by the test scripts.
 *
 * @author tahanima
 * @since 01/26/2022
 */
@Listeners(TestListener.class)
public abstract class BaseTest {
    private final WebDriver driver = DriverManager.createDriver();

    public abstract void initialize();

    protected <T extends BasePage> T createInstance(final Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setup() {
        initialize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
