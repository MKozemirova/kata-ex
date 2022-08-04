package com.gildedrose.quality;

import static com.gildedrose.quality.QualityManagerFactory.getManager;

import com.gildedrose.GildedRoseItem;

public class QualityService {

    public int calculateDelta(GildedRoseItem item) {
        QualityCalculator manager = getManager(item.getName());
        int initialDelta = manager.calculateDelta(item.getQuality(), item.getSellIn());

        return item.isConjured() ? initialDelta*2 : initialDelta;
    }
}
