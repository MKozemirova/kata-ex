package com.gildedrose;

import com.gildedrose.quality.QualityService;
import com.gildedrose.sellin.SellinService;
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

    private final QualityService qualityService;
    private final SellinService sellinService;

    public GildedRose(Item[] items, Set<String> conjuredItems, QualityService qualityService, SellinService sellinService) {
        this.items = items;
        this.conjuredItems = conjuredItems;
        this.qualityService = qualityService;
        this.sellinService = sellinService;
    }

    public void updateQuality() {
        for (Item item : items) {
            GildedRoseItem gildedRoseItem = new GildedRoseItem(conjuredItems.contains(item.name), item);
            item.quality += qualityService.calculateDelta(gildedRoseItem);
            item.sellIn += sellinService.calculateDelta(gildedRoseItem);
        }
    }
}