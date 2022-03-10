package io.github.tahanima.data.login;

import com.univocity.parsers.annotations.Parsed;
import io.github.tahanima.data.BaseData;
import lombok.Getter;

/**
 * This class captures all the test data properties
 * required for the login page.
 *
 * @author tahanima
 * @since 01/29/2022
 */
public class LoginData extends BaseData {
    @Getter
    @Parsed(field = "User Name", defaultNullRead = "")
    private String userName;

    @Getter
    @Parsed(field = "Password", defaultNullRead = "")
    private String password;

    @Getter
    @Parsed(field = "Error Message", defaultNullRead = "")
    private String errorMessage;

    @Override
    public String toString() {
        return String.format("{%s, userName=%s, password=%s, errorMessage=%s}",
                super.toString(),
                userName,
                password,
                errorMessage);
    }
}
