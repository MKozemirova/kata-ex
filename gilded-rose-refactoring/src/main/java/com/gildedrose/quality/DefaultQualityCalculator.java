package com.gildedrose.quality;

import com.gildedrose.configuration.AppConfig;
import com.gildedrose.configuration.ConfigReader;

public class DefaultQualityCalculator implements QualityCalculator {

    private static AppConfig config = AppConfig.getInstance(ConfigReader.getInstance());

    @Override
    public int calculateDelta(int quality, int sellIn) {
        if (quality > config.getMinQuality()) {
            if (sellIn < 0 && quality > 1) {
                return -2;
            }
            return -1;
        }
        return 0;
    }

    @Override
    public boolean match(String itemName) {
        return true;
    }
}
