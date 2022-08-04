package com.gildedrose.quality;

/**
 * todo: remove the useless class
 */
public class SulfurasQualityCalculator implements QualityCalculator {


    /**
     * Hardcode this one item to avoid having 100500 mechanisms
     */
    @Override
    public int calculateDelta(int quality, int sellIn) {
        return 0;
    }

    @Override
    public boolean match(String itemName) {
        return itemName.equals("Sulfuras, Hand of Ragnaros");
    }
}
