package io.github.tahanima.page.login;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import io.github.tahanima.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class captures the relevant UI components and
 * functionalities of the login page.
 *
 * @author tahanima
 * @since 01/25/2022
 */
public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    public void goTo() {
        getDriver().get(configuration().baseUrl());
    }

    public LoginPage enterUsername(final String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);

        return this;
    }

    public LoginPage enterPassword(final String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);

        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
