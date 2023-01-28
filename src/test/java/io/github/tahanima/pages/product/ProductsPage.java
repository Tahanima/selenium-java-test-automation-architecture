package io.github.tahanima.pages.product;

import io.github.tahanima.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class captures only those features needed to support test functionalities of the login page.
 *
 * @author tahanima
 */
public final class ProductsPage extends BasePage {
    @FindBy(className = "title")
    private WebElement lblTitle;

    public String getTitle() {
        return lblTitle.getText();
    }
}
