package com.gildedrose.quality;

import com.gildedrose.Item;

public interface ItemManager {

    int calculateNewQuality(Item item);

    default int calculateNewSellIn(Item item) {
        return item.sellIn - 1;
    }
}
