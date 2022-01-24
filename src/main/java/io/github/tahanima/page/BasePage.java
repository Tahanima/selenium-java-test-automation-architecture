package io.github.tahanima.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * This class defines the basic functionalities of a POM class.
 *
 * @author tahanima
 * @since 01/25/2022
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
}
