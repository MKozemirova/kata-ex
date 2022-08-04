package com.gildedrose.quality;

public class BackstagePassesQualityCalculator implements QualityCalculator {

    @Override
    public int calculateDelta(int quality, int sellIn) {
        if (sellIn < 0) {
            return -quality;
        }
        if (quality >= 50) {
            return 0;
        }
        if (sellIn < 6) {
            return 3;
        }
        if (sellIn < 11) {
            return 2;
        }
        return 1;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
