package com.gildedrose;

import com.gildedrose.quality.QualityService;
import com.gildedrose.sellin.SellInService;

/**
 *  FIXME: 7/28/22
 *  1) + Add tests
 *  2) Create an interface with conjured items - not needed
 *  3) + Commit every successful change
 *  4) + Separate calculate quality from calculate sell in
 */
public class GildedRose {
    Item[] items;
    private final QualityService qualityService;
    private final SellInService sellinService;

    public GildedRose(Item[] items, QualityService qualityService, SellInService sellinService) {
        this.items = items;
        this.qualityService = qualityService;
        this.sellinService = sellinService;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.sellIn += sellinService.calculateDelta(item);
            item.quality += qualityService.calculateDelta(item);
        }
    }
}