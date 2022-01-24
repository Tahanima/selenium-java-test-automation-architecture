package io.github.tahanima.driver;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import org.openqa.selenium.WebDriver;

/**
 * This class provides a singleton instance of WebDriver.
 *
 * @author tahanima
 * @since 01/24/2022
 */
public final class DriverManager {
    private static WebDriver driver = null;

    private DriverManager() { }

    /**
     * @return instance of browser driver implementation
     */
    public static WebDriver createDriver() {
        return (driver == null)
                ? BrowserFactory.valueOf(configuration().browser().toUpperCase())
                .initializeDriver()
                : driver;
    }
}
