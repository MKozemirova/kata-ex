package com.gildedrose.quality;

/**
 * TODO:
 * 1) Create a list of all managers except DefaultItemManager and iterate to get an instance
 * 2) If no instance -> default
 * 3) Try switch expression to not forget classes
 */
public class QualityManagerFactory {
    public static ItemManager createManager(String itemName) {

        if (itemName.equals("Aged Brie"))
            return new AgedBrieItemManager();
        if (itemName.equals("Backstage passes to a TAFKAL80ETC concert"))
            return new BackstagePassesItemManager();
        if (itemName.equals("Sulfuras, Hand of Ragnaros"))
            return new SulfurasItemManager();
        return new DefaultItemManager();
    }
}
