package io.github.tahanima.driver;

import static io.github.tahanima.config.ConfigurationManager.configuration;
import static java.lang.Boolean.TRUE;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * This enum handles the initialization of browser drivers.
 *
 * <p>
 * This project makes use of the drivers - chrome, firefox and safari currently.
 * So, this enum contains the initialization logic of these browser drivers.
 * </p>
 *
 * @author tahanima
 * @since 01/24/2022
 */
public enum BrowserFactory {
    /**
     * Contains all the initialization logic
     * for the chrome driver.
     */
    CHROME {
        @Override
        public WebDriver initializeDriver() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(getOptions());

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    configuration().timeout()));
            driver.manage().window().maximize();

            return driver;
        }

        private ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();

            options.setAcceptInsecureCerts(true);
            options.setHeadless(configuration().headless());

            return options;
        }
    },

    /**
     * Contains all the initialization logic
     * for the firefox driver.
     */
    FIREFOX {
        @Override
        public WebDriver initializeDriver() {
            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver(getOptions());

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    configuration().timeout()));
            driver.manage().window().maximize();

            return driver;
        }

        private FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();

            options.setAcceptInsecureCerts(true);
            options.setHeadless(configuration().headless());

            return options;
        }
    },

    /**
     * Contains all the initialization logic
     * for the safari driver.
     */
    SAFARI {
        @Override
        public WebDriver initializeDriver() {
            WebDriverManager.safaridriver().setup();
            WebDriver driver = new SafariDriver(getOptions());

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    configuration().timeout()));
            driver.manage().window().maximize();

            return driver;
        }

        private SafariOptions getOptions() {
            SafariOptions options = new SafariOptions();

            options.setAcceptInsecureCerts(true);

            if (TRUE.equals(configuration().headless())) {
                throw new IllegalStateException(String.format(
                        "Headless not supported for %s browser",
                        configuration().browser()));
            }

            return options;
        }
    };

    /**
     * @return an instance of browser driver implementation
     */
    public abstract WebDriver initializeDriver();
}
