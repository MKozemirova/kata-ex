package com.gildedrose;

import static com.gildedrose.quality.ConjuredItemsManager.calculateConjuredQuality;

import com.gildedrose.quality.ItemManager;
import com.gildedrose.quality.QualityManagerFactory;

public class ItemUpdater {
    public void update(Item item, boolean isConjured) {
        ItemManager itemManager = QualityManagerFactory.createManager(item.name);
        item.quality = calculateConjuredQuality(itemManager.calculateNewQuality(item), isConjured);
        item.sellIn = itemManager.calculateNewSellIn(item);
    }
}
