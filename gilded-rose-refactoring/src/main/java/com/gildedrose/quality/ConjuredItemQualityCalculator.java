package com.gildedrose.quality;

import static java.lang.Math.max;

import com.gildedrose.configuration.AppConfig;
import com.gildedrose.configuration.ConfigReader;

@CustomItemCalculator
public class ConjuredItemQualityCalculator implements QualityCalculator {

    private static final AppConfig config = AppConfig.getInstance(ConfigReader.getInstance());
    @Override
    public int calculateDelta(int quality, int sellIn) {
        if (quality > config.getMinQuality()) {
            int targetDelta = sellIn < 0 ? -4 : -2;
            return max(targetDelta, -quality);
        }
        return 0;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.contains("Conjured");
    }
}
