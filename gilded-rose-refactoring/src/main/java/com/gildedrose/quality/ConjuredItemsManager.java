package com.gildedrose.quality;

public class ConjuredItemsManager {

    public static int calculateConjuredQuality(int quantity, boolean conjured) {
        if (conjured)
            return quantity*2;
        return quantity;
    }
}
