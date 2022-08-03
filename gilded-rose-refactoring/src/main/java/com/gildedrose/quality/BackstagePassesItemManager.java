package com.gildedrose.quality;

import com.gildedrose.Item;

public class BackstagePassesItemManager implements ItemManager {

    @Override
    public int calculateNewQuality(Item item) {
        if (item.sellIn < 0) {
            return 0;
        }
        if (item.quality < 50) {
            if (item.sellIn < 6) {
                return item.quality + 3;
            }
            if (item.sellIn < 11) {
                return item.quality + 2;
            }
            return item.quality + 1;
        }
        return item.quality;
    }
}
