package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            props.load(in);
        } catch (IOException e) {
            // Fail fast if we can’t read the file
            throw new RuntimeException("Could not load config.properties from project root", e);
        }
    }

    /**
     * @param key the property name (e.g. "user.primary.email")
     * @return the value, or null if missing
     */
    public static String get(String key) {
        return props.getProperty(key);
    }

}
