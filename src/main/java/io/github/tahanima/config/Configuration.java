package io.github.tahanima.config;

import org.aeonbits.owner.Config;

/**
 * This interface loads the global parameters
 * contained within general.properties file.
 *
 * @author tahanima
 * @since 01/24/2022
 */
@Config.Sources({ "classpath:general.properties" })
public interface Configuration extends Config {
    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("timeout")
    int timeout();

    @Key("base.url")
    String baseUrl();
}
