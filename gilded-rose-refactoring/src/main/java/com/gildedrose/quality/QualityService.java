package com.gildedrose.quality;

import static com.gildedrose.quality.QualityManagerFactory.getManager;

import com.gildedrose.Item;

public class QualityService {

    public int calculateDelta(Item item) {
        QualityCalculator manager = getManager(item.name);
        int initialDelta = manager.calculateDelta(item.quality, item.sellIn);

        return item.name.contains("Conjured") ? initialDelta*2 : initialDelta;
    }
}
