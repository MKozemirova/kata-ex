package com.gildedrose.quality;

import com.gildedrose.configuration.AppConfig;
import com.gildedrose.configuration.ConfigReader;

@CustomItemCalculator
public class AgedBrieQualityCalculator implements QualityCalculator {

    private static final AppConfig config = AppConfig.getInstance(ConfigReader.getInstance());

    @Override
    public int calculateDelta(int quality, int sellIn) {
        return quality < config.getMaxQuality() ? 1 : 0;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.equals("Aged Brie");
    }
}
