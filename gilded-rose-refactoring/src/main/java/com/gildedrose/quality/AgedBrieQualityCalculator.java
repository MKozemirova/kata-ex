package com.gildedrose.quality;

public class AgedBrieQualityCalculator implements QualityCalculator {

    @Override
    public int calculateDelta(int quality, int sellIn) {
        return quality < 50 ? 1 : 0;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.equals("Aged Brie");
    }
}
