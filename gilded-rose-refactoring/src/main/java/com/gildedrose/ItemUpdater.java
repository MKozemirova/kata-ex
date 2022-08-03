package com.gildedrose;

import static com.gildedrose.quality.ConjuredItemsManager.calculateConjuredQuality;

import com.gildedrose.quality.ItemManager;
import com.gildedrose.quality.QualityManagerFactory;

public class ItemUpdater {

    private final GildedRoseCalculator qualityCalculator;
    private final GildedRoseCalculator sellinCalculator;

    public ItemUpdater() {
        this.qualityCalculator = new QualityCalculator();
        this.sellinCalculator = new SellinCalculator();
    }

    public void update(Item item, boolean isConjured) {
        ItemManager itemManager = QualityManagerFactory.createManager(item.name);
        GildedRoseItem gildedRoseItem = new GildedRoseItem(isConjured, item);
        item.quality = calculateConjuredQuality(itemManager.calculateNewQuality(item), isConjured);
        item.sellIn += sellinCalculator.calculateDelta(gildedRoseItem);
    }
}
