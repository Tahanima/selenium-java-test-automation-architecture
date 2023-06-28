package io.github.tahanima.ui.page;

import static io.github.tahanima.config.ConfigurationManager.config;

import io.github.tahanima.factory.BasePageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author tahanima
 */
public final class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    public LoginPage open() {
        driver.get(config().baseUrl());

        return this;
    }

    private void clearAndType(WebElement elem, String text) {
        elem.clear();
        elem.sendKeys(text);
    }

    public LoginPage typeUsername(final String username) {
        clearAndType(txtUsername, username);

        return this;
    }

    public LoginPage typePassword(final String password) {
        clearAndType(txtPassword, password);

        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(By.className("error-message-container"))
                .findElement(By.tagName("h3"))
                .getText();
    }

    public ProductsPage clickOnLogin() {
        btnLogin.click();

        return BasePageFactory.createInstance(driver, ProductsPage.class);
    }

    public ProductsPage loginAs(String username, String password) {
        open();
        typeUsername(username);
        typePassword(password);

        return clickOnLogin();
    }
}
