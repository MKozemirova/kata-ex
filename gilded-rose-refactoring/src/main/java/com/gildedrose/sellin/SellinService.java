package com.gildedrose.sellin;

import com.gildedrose.GildedRoseItem;

public class SellinService {

    public int calculateDelta(GildedRoseItem item) {
        if(item.getName().equals("Sulfuras, Hand of Ragnaros")) {
            return 0;
        }
        return -1;
    }
}
