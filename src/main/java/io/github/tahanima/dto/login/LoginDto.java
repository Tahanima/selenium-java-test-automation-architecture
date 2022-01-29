package io.github.tahanima.dto.login;

import com.univocity.parsers.annotations.Parsed;
import io.github.tahanima.dto.BaseDto;

/**
 * @author tahanima
 * @since 01/29/2022
 */
public class LoginDto extends BaseDto {
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
        return "LoginDto{"
                + super.toString()
                + ", userName=" + userName
                + ", password=" + password
                + ", errorMessage=" + errorMessage
                + '}';
    }
}
