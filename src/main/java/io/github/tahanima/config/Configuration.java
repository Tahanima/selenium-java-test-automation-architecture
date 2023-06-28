package io.github.tahanima.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;

/**
 * @author tahanima
 */
@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("timeout")
    int timeout();

    @Key("base.url")
    String baseUrl();

    @Key("base.test.data.path")
    String baseTestDataPath();

    @Key("base.report.path")
    String baseReportPath();

    @Key("base.screenshot.path")
    String baseScreenshotPath();
}
