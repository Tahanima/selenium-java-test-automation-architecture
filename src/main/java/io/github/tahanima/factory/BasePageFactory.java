package io.github.tahanima.factory;

import io.github.tahanima.ui.page.BasePage;

import org.openqa.selenium.WebDriver;

/**
 * @author tahanima
 */
public final class BasePageFactory {

    private BasePageFactory() {}

    public static <T extends BasePage> T createInstance(
            final WebDriver driver, final Class<T> clazz) {
        try {
            BasePage instance = clazz.getDeclaredConstructor().newInstance();

            instance.initDriverAndElements(driver);
            instance.initComponents();

            return clazz.cast(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new NullPointerException("Page class instantiation failed.");
    }
}
