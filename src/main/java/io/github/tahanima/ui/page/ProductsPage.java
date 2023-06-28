package io.github.tahanima.ui.page;

import io.github.tahanima.ui.component.Header;
import io.github.tahanima.ui.component.SideNavMenu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author tahanima
 */
public final class ProductsPage extends BasePage {

    private Header header;
    private SideNavMenu sideNavMenu;

    @FindBy(className = "title")
    private WebElement lblTitle;

    @Override
    public void initComponents() {
        header = new Header(driver);
        sideNavMenu = new SideNavMenu(driver);
    }

    public void clickOnLogout() {
        header.clickOnHamburgerIcon();
        sideNavMenu.clickOnLogout();
    }

    public String getTitle() {
        return lblTitle.getText();
    }
}
