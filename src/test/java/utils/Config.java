package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();
    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            PROPS.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }
    public static String get(String key) {
        return PROPS.getProperty(key);
    }
}
