package com.gildedrose.quality;

@CustomItemCalculator
public class SulfurasQualityCalculator implements QualityCalculator {

    @Override
    public int calculateDelta(int quality, int sellIn) {
        return 0;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.equals("Sulfuras, Hand of Ragnaros");
    }
}
