package utils;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();
    static {
        try {
            PROPS.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }
    public static String get(String key) {
        return PROPS.getProperty(key);
    }
}
