package com.gildedrose.configuration;

public class AppConfig {

    private static AppConfig instance;
    private final int maxQuality;
    private final int minQuality;

    private AppConfig(ConfigReader configReader) {
        maxQuality = configReader.getProperty("app.max-quality", Integer::parseInt);
        minQuality = configReader.getProperty("app.min-quality", Integer::parseInt);
    }

    public static AppConfig getInstance(ConfigReader configReader) {
        if (instance == null) {
            instance = new AppConfig(configReader);
        }
        return instance;
    }

    public int getMaxQuality() {
        return maxQuality;
    }

    public int getMinQuality() {
        return minQuality;
    }
}
