package io.github.tahanima.page;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static io.github.tahanima.config.ConfigurationManager.configuration;

/**
 * This class defines the basic functionalities of a POM class.
 *
 * @author tahanima
 */
public class BasePage {
    private WebDriver driver;

    public void initialize(final WebDriver webdriver) {
        this.driver = webdriver;

        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void captureScreenshot(String fileName) {
        Shutterbug.shootPage(driver)
                .withName(fileName)
                .save(configuration().baseReportPath() + configuration().baseScreenshotPath());
    }
}
