package com.gildedrose.sellin;

import com.gildedrose.Item;

public class SellinService {

    public int calculateDelta(Item item) {
        if(item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return 0;
        }
        return -1;
    }
}
