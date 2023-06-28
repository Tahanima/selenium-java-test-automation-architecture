package io.github.tahanima.ui.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author tahanima
 */
public final class SideNavMenu extends BaseComponent {

    public SideNavMenu(WebDriver driver) {
        super(driver);
    }

    public void clickOnLogout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
