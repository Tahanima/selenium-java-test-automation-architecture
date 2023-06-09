package io.github.tahanima.page.login;

import static io.github.tahanima.config.ConfigurationManager.config;

import io.github.tahanima.page.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class captures the relevant UI components and functionalities of the login page.
 *
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

    public LoginPage typeUsername(final String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);

        return this;
    }

    public LoginPage typePassword(final String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);

        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(By.className("error-message-container"))
                .findElement(By.tagName("h3"))
                .getText();
    }

    public void clickOnLogin() {
        btnLogin.click();
    }
}
