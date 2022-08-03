package com.gildedrose;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items, new HashSet<>(), new ItemUpdater());
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void updateRegularItem() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 2, 8),
        };
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 7),
                new ItemData(0, 6),
                new ItemData(-1, 5),
                new ItemData(-2, 3),
                new ItemData(-3, 1),
                new ItemData(-4, 0),
                new ItemData(-5, 0)
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateAgedBrie() {
        Item[] items = new Item[] {
                new Item("Aged Brie", 2, 47),
        };
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 48),
                new ItemData(0, 49),
                new ItemData(-1, 50),
                new ItemData(-2, 50),
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateSulfuras() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
        };

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(0, 80),
                new ItemData(0, 80),
                new ItemData(0, 80)
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateBackStageNewPasses() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 13, 12),
        };

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(12, 13),
                new ItemData(11, 14),
                new ItemData(10, 15)
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateBackStageMediumPasses() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 12),
        };

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(8, 14),
                new ItemData(7, 16),
                new ItemData(6, 18),
                new ItemData(5, 20)
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateBackStageHotPasses() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 3, 12),
        };

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(2, 15),
                new ItemData(1, 18),
                new ItemData(0, 21),
                new ItemData(-1, 24),
                new ItemData(-2, 0)
        };

        GildedRose app = new GildedRose(items, Collections.emptySet(), new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateConjuredItem() {
        Item[] items = new Item[] {
                new Item("Conjured Item", 2, 8),
        };
        Set<String> conjuredItems = Set.of("Conjured Item");
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 6),
                new ItemData(0, 4),
                new ItemData(-1, 2),
                new ItemData(-2, 0),
                new ItemData(-3, 0),
        };

        GildedRose app = new GildedRose(items, conjuredItems, new ItemUpdater());
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellin(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    record ItemData(int sellin, int quality) { }
}
