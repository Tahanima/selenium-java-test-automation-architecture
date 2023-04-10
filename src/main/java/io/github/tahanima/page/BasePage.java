package io.github.tahanima.page;

import static io.github.tahanima.config.ConfigurationManager.config;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * This class defines the basic functionalities of a POM class.
 *
 * @author tahanima
 */
public class BasePage {
    protected WebDriver driver;

    public void initialize(final WebDriver webdriver) {
        this.driver = webdriver;

        PageFactory.initElements(driver, this);
    }

    public void captureScreenshot(String fileName) {
        Shutterbug.shootPage(driver).withName(fileName).save(config().baseScreenshotPath());
    }
}
