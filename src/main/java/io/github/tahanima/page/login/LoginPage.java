package io.github.tahanima.page.login;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import io.github.tahanima.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class captures the relevant UI components and
 * functionalities of the login page.
 *
 * @author tahanima
 * @since 01/25/2022
 */
public final class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    public LoginPage goTo() {
        getDriver().get(configuration().baseUrl());

        return this;
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

    public String getErrorMessage() {
        return getDriver()
                .findElement(By.className("error-message-container"))
                .findElement(By.tagName("h3"))
                .getText();
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
