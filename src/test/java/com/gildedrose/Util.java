package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.model.Item;

public class Util {

    private Util(){}

    public static void assertItem(Item item,
        String expectedItemName,
        int expectedSellIn,
        int expectedQuality) {
        assertEquals(expectedItemName, item.name);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }

}
