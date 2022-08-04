package com.gildedrose;

import com.gildedrose.quality.QualityService;
import com.gildedrose.sellin.SellinService;
import java.util.Set;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = singleItem("foo", 0, 0);
        GildedRose app = gildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void regularItemQualityDecreasesByOne() {
        Item[] items = singleItem("+5 Dexterity Vest", 5, 8);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(4, 7),
                new ItemData(3, 6),
                new ItemData(2, 5),
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void regularItemQualityIsAlwaysPositive() {
        Item[] items = singleItem("Elixir of the Mongoose", 1, 2);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(0, 1),
                new ItemData(-1, 0),
                new ItemData(-2, 0)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void qualityDecreasesTwiceFasterAfterSellDate() {
        Item[] items = singleItem("+5 Dexterity Vest", 2, 20);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 19),
                new ItemData(0, 18),
                new ItemData(-1, 16)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void agedBrieQualityIncreasesByOne() {
        Item[] items = singleItem("Aged Brie", 2, 5);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 6),
                new ItemData(0, 7),
                new ItemData(-1, 8)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void agedBrieQualityAlwaysLessThanFifty() {
        Item[] items = singleItem("Aged Brie", 2, 48);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 49),
                new ItemData(0, 50),
                new ItemData(-1, 50)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void sulfurasNeverChanges() {
        Item[] items = singleItem("Sulfuras, Hand of Ragnaros", 0, 80);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(0, 80),
                new ItemData(0, 80),
                new ItemData(0, 80)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void backstagePassesIncreaseInQuality() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 13, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(12, 13),
                new ItemData(11, 14),
                new ItemData(10, 15)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateBackStageMediumPasses() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 9, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(8, 14),
                new ItemData(7, 16),
                new ItemData(6, 18),
                new ItemData(5, 20)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateBackStageHotPasses() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 3, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(2, 15),
                new ItemData(1, 18),
                new ItemData(0, 21),
                new ItemData(-1, 0),
                new ItemData(-2, 0)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void updateConjuredItem() {
        Item[] items = singleItem("Conjured Item", 2, 8);
        Set<String> conjuredItems = Set.of("Conjured Item");
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 6),
                new ItemData(0, 4),
                new ItemData(-1, 0),
                new ItemData(-2, 0),
                new ItemData(-3, 0),
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    private static Item[] singleItem(String name, int sellIn, int quality) {
        return new Item[]{new Item(name, sellIn, quality)};
    }

    private static GildedRose gildedRose(Item[] items) {
        return new GildedRose(items, new QualityService(), new SellinService());
    }

    public record ItemData(int sellIn, int quality) { }
}
