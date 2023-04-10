package io.github.tahanima.data.login;

import com.univocity.parsers.annotations.Parsed;

import io.github.tahanima.data.BaseTestData;

import lombok.Getter;
import lombok.ToString;

/**
 * This class captures all the test data properties required for the login page.
 *
 * @author tahanima
 */
@Getter
@ToString(callSuper = true)
public class LoginTestData extends BaseTestData {
    @Parsed(field = "User Name", defaultNullRead = "")
    private String userName;

    @Parsed(field = "Password", defaultNullRead = "")
    private String password;

    @Parsed(field = "Error Message", defaultNullRead = "")
    private String errorMessage;
}
