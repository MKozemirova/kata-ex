package com.gildedrose.quality;

public interface QualityCalculator {

    int calculateDelta(int quality, int sellIn);

    boolean match(String itemName);

}
