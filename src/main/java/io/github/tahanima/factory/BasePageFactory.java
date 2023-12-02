package io.github.tahanima.factory;

import io.github.tahanima.ui.page.BasePage;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

/**
 * @author tahanima
 */
@Slf4j
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
            log.error("BasePageFactory::createInstance", e);
        }

        throw new NullPointerException("Page class instantiation failed.");
    }
}
