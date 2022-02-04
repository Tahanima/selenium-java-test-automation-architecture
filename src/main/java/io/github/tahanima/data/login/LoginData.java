package io.github.tahanima.data.login;

import com.univocity.parsers.annotations.Parsed;
import io.github.tahanima.data.BaseData;

/**
 * This class captures all the test data properties
 * required for the login page.
 *
 * @author tahanima
 * @since 01/29/2022
 */
public class LoginData extends BaseData {
    @Parsed(field = "User Name")
    private String userName;

    @Parsed(field = "Password")
    private String password;

    @Parsed(field = "Error Message")
    private String errorMessage;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return String.format("{%s, userName=%s, password=%s, errorMessage=%s}",
                super.toString(),
                userName,
                password,
                errorMessage);
    }
}
