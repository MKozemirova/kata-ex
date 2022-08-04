package com.gildedrose.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

public class ConfigReader {

    private static ConfigReader instance;

    private final Properties appProperties;

    private ConfigReader() {
        String propertiesFilePath = String.format("%s/app.properties",
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath());
        appProperties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(propertiesFilePath)) {
            appProperties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Unable to load properties file from '%s'", propertiesFilePath));
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public <T> T getProperty(String propertyName, Function<String, T> castFunction) {
        return castFunction.apply(appProperties.getProperty(propertyName));
    }
}
