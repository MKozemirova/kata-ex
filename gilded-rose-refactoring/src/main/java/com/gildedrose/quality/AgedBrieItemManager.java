package com.gildedrose.quality;

import com.gildedrose.Item;

public class AgedBrieItemManager implements ItemManager {

    @Override
    public int calculateNewQuality(Item item) {
        if (item.quality < 50) {
            return item.quality + 1;
        }
        return item.quality;
    }
}
