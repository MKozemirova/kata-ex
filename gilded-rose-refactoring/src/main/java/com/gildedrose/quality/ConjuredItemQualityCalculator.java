package com.gildedrose.quality;

import static java.lang.Math.max;

public class ConjuredItemQualityCalculator implements QualityCalculator {

    @Override
    public int calculateDelta(int quality, int sellIn) {
        if (quality > 0) {
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
