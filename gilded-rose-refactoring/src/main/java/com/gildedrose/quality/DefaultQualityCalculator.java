package com.gildedrose.quality;

public class DefaultQualityCalculator implements QualityCalculator {

    @Override
    public int calculateDelta(int quality, int sellIn) {
        if (quality > 0) {
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
