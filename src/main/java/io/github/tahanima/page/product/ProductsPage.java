package io.github.tahanima.page.product;

import io.github.tahanima.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author tahanima
 * @since 01/29/2022
 */
public class ProductsPage extends BasePage {
    @FindBy(className = "title")
    private WebElement lblTitle;

    public String getTitle() {
        return lblTitle.getText();
    }
}
