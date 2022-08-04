package com.gildedrose;

import com.gildedrose.quality.QualityService;
import com.gildedrose.quality.util.QualityCalculatorFactory;
import com.gildedrose.sellin.SellInService;
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
    void qualityAlwaysLessThanFifty() {
        Item[] items = new Item[]{
               new Item("Aged Brie", 2, 48),
                new Item("Backstage passes to a TAFKAL80ETC concert", 20, 48)
        };
        ItemData[][] expectedItemData = new ItemData[][] {
                new ItemData[] {new ItemData(1, 49), new ItemData(19, 49)},
                new ItemData[] {new ItemData(0, 50), new ItemData(18, 50)},
                new ItemData[]{new ItemData(-1, 50), new ItemData(17, 50)}
        };

        GildedRose app = gildedRose(items);
        for (ItemData[] expectedItem : expectedItemData) {
            app.updateQuality();
            for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
                assertEquals(expectedItem[itemIndex].sellIn(), items[itemIndex].sellIn);
                assertEquals(expectedItem[itemIndex].quality(), items[itemIndex].quality);
            }
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
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 20, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(19, 13),
                new ItemData(18, 14),
                new ItemData(17, 15)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void backstagePassesLess10DaysIncreaseQualityTwice() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 12, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(11, 13),
                new ItemData(10, 15),
                new ItemData(9, 17),
                new ItemData(8, 19),
                new ItemData(7, 21),
                new ItemData(6, 23)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void backstagePassesLessThan5DaysIncreaseQualityByThree() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 7, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(6, 14),
                new ItemData(5, 17),
                new ItemData(4, 20)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void backstagePassesWithLessThanZeroSellInHaveZeroQuality() {
        Item[] items = singleItem("Backstage passes to a TAFKAL80ETC concert", 2, 12);

        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 15),
                new ItemData(0, 18),
                new ItemData(-1, 0)
        };

        GildedRose app = gildedRose(items);
        for (ItemData expectedItem : expectedItemData) {
            app.updateQuality();
            assertEquals(expectedItem.sellIn(), items[0].sellIn);
            assertEquals(expectedItem.quality(), items[0].quality);
        }
    }

    @Test
    void conjuredItemsDegradeTwiceFaster() {
        Item[] items = singleItem("Conjured Mana Cake", 2, 3);
        ItemData[] expectedItemData = new ItemData[] {
                new ItemData(1, 1),
                new ItemData(0, 0),
                new ItemData(-1, 0)
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
        return new GildedRose(items, new QualityService(QualityCalculatorFactory.getInstance()), new SellInService());
    }

    public record ItemData(int sellIn, int quality) { }
}
