package com.gildedrose.quality;

import com.gildedrose.Item;

/**
 * todo: remove the useless class
 */
public class SulfurasItemManager implements ItemManager {


    /**
     * Hardcode this one item to avoid having 100500 mechanisms
     */
    @Override
    public int calculateNewQuality(Item item) {
        return item.quality;
    }

    /**
     * Hardcode this one item to avoid having 100500 mechanisms
     */
    @Override
    public int calculateNewSellIn(Item item) {
        return item.sellIn;
    }
}
