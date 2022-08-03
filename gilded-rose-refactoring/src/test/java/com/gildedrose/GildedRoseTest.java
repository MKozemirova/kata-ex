package com.gildedrose;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items, new HashMap<>(), new ItemUpdater());
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
}
