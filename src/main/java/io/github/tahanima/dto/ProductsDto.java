package io.github.tahanima.dto;

import com.univocity.parsers.annotations.Parsed;

import lombok.Getter;
import lombok.ToString;

/**
 * @author tahanima
 */
@Getter
@ToString(callSuper = true)
public final class ProductsDto extends BaseDto {

    @Parsed(field = "User Name", defaultNullRead = "")
    private String username;

    @Parsed(field = "Password", defaultNullRead = "")
    private String password;

    @Parsed(field = "URL", defaultNullRead = "")
    private String url;
}
