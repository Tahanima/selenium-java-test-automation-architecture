package io.github.tahanima.ui.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author tahanima
 */
public final class Header extends BaseComponent {

    public Header(WebDriver driver) {
        super(driver);
    }

    public void clickOnHamburgerIcon() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }
}
