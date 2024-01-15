package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static final Properties config = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            config.load(input);
        } catch (IOException e) {
            System.out.println("Configuration File not found.");
            System.exit(1);
        }
    }

    public static String getDbPassword() {
        return config.getProperty("db.password");
    }

    public static String getApiKeyGPT() {
        return config.getProperty("api.key.GPT");
    }

    public static String getApiKeyGPT2() {
        return config.getProperty("api.key.GPT2");
    }

    public static String getApiKeyMaps() {
        return config.getProperty("api.key.maps");
    }
}
