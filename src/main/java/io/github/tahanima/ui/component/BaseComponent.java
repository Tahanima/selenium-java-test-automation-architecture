package io.github.tahanima.ui.component;

import org.openqa.selenium.WebDriver;

/**
 * @author tahanima
 */
public abstract class BaseComponent {

    protected WebDriver driver;

    protected BaseComponent(WebDriver driver) {
        this.driver = driver;
    }
}
