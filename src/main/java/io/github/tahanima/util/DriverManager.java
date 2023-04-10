package io.github.tahanima.util;

import static io.github.tahanima.config.ConfigurationManager.config;

import org.openqa.selenium.WebDriver;

/**
 * This class provides a singleton instance of WebDriver.
 *
 * @author tahanima
 */
public final class DriverManager {
    private static WebDriver driver = null;

    private DriverManager() {}

    /**
     * @return an instance of browser driver implementation
     */
    public static WebDriver createDriver() {
        if (driver == null) {
            driver =
                    BrowserFactory.valueOf(config().browser().toUpperCase())
                            .initializeDriver();
        }

        return driver;
    }
}
