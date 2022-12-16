package io.github.tahanima.data.login;

import com.univocity.parsers.annotations.Parsed;
import io.github.tahanima.data.BaseData;
import lombok.Getter;
import lombok.ToString;

/**
 * This class captures all the test data properties required for the login page.
 *
 * @author tahanima
 */
@ToString(callSuper = true)
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
}
