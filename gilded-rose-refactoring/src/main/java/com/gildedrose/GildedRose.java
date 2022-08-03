package com.gildedrose;

import java.util.Map;
import java.util.Set;

/**
 *  FIXME: 7/28/22
 *  1) Add tests
 *  2) Create an interface with conjured items
 *  3) Commit every successful change
 *  4) Separate calculate quality from calculate sell in
 */
class GildedRose {
    Item[] items;
    Set<String> conjuredItems;

    private final ItemUpdater itemUpdater;

    public GildedRose(Item[] items, Set<String> conjuredItems, ItemUpdater itemUpdater) {
        this.items = items;
        this.conjuredItems = conjuredItems;
        this.itemUpdater = itemUpdater;
    }

    public void updateQuality() {
        for (Item item : items) {
            itemUpdater.update(item, conjuredItems.contains(item.name));
        }
    }
}