package io.github.tahanima.ui.page;

import static io.github.tahanima.config.ConfigurationManager.config;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author tahanima
 */
public abstract class BasePage {

    protected WebDriver driver;

    public void initDriverAndElements(final WebDriver webdriver) {
        this.driver = webdriver;

        PageFactory.initElements(driver, this);
    }

    public void initComponents() {}

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void captureScreenshot(String fileName) {
        Shutterbug.shootPage(driver).withName(fileName).save(config().baseScreenshotPath());
    }
}
