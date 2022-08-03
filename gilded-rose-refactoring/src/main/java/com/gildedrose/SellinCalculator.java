package com.gildedrose;

public class SellinCalculator implements GildedRoseCalculator {

    @Override
    public int calculateDelta(GildedRoseItem item) {
        if(item.getName().equals("Sulfuras, Hand of Ragnaros")) {
            return item.getSellIn();
        }
        return -1;
    }
}
