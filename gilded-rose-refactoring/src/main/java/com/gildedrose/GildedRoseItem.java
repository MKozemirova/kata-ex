package com.gildedrose;

public class GildedRoseItem {
    private final boolean conjured;
    private final Item item;

    public GildedRoseItem(boolean conjured, Item item) {
        this.conjured = conjured;
        this.item = item;
    }

    public boolean isConjured() {
        return conjured;
    }

    public String getName() {
        return item.name;
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public int getQuality() {
        return item.quality;
    }
}
