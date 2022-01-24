package io.github.tahanima.config;

import org.aeonbits.owner.ConfigCache;

/**
 *  This class provides a singleton instance of
 *  the Configuration class.
 *
 * @author tahanima
 * @since 01/24/2022
 */
public final class ConfigurationManager {
    private ConfigurationManager() {  }

    /**
     * @return an instance of Configuration class from an internal cache
     * @see <a href="http://owner.aeonbits.org/docs/singleton">reference</a>
     */
    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
