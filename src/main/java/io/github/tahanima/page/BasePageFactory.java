package io.github.tahanima.page;

import org.openqa.selenium.WebDriver;

/**
 * This class provides an instance of subclass of BasePage.
 *
 * @author tahanima
 * @since 01/25/2022
 */
public final class BasePageFactory {
    private BasePageFactory() { }

    /**
     * @param driver browser driver instance
     * @param page POM class instance
     * @param <T> subclass of BasePage
     * @return a new instance of the POM class
     */
    public static <T extends BasePage> T createInstance(final WebDriver driver,
                                                        final Class<T> page) {
        try {
            BasePage instance = page.getDeclaredConstructor().newInstance();
            instance.initialize(driver);
            return page.cast(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new NullPointerException("Page class instantiation failed.");
    }
}
