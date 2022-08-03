package com.gildedrose.quality;

import com.gildedrose.Item;

public class DefaultItemManager implements ItemManager {

    @Override
    public int calculateNewQuality(Item item) {
        if (item.quality > 0) {
            if (item.sellIn < 0) {
                return item.quality - 2;
            }
            return item.quality - 1;
        }
        return item.quality;
    }
}
