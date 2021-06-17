package com.gildedrose;

import com.gildedrose.model.Item;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @ParameterizedTest
    @MethodSource("provideStandardItemsAndExpectationsSuccess")
    void testUpdateQualityStandardProduct(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        applyAndAssert(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideStandardItemsAndExpectationsSuccess() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("Dexterity Vest", 2, 10, 1, 9),
            Arguments.of("Dexterity Vest", 1, 9, 0, 8),
            Arguments.of("Dexterity Vest", 0, 7, -1, 5),
            Arguments.of("Dexterity Vest", -1, 5, -2, 3),
            Arguments.of("Dexterity Vest", -2, 3, -3, 1),
            Arguments.of("Dexterity Vest", -3, 1, -4, 0),
            Arguments.of("Dexterity Vest", -4, 0, -5, 0),
            Arguments.of("Dexterity Vest", -5, -1, -6, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBrieItemsAndExpectationsSuccess")
    void testUpdateQualityBrie(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        applyAndAssert(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideBrieItemsAndExpectationsSuccess() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("Aged Brie", 3, 4, 2, 5),
            Arguments.of("Aged Brie", 2, 5, 1, 6),
            Arguments.of("Aged Brie", 1, 6, 0, 7),
            Arguments.of("Aged Brie", 0, 7, -1, 9),
            Arguments.of("Aged Brie", -1, 9, -2, 11),
            Arguments.of("Aged Brie", -2, 11, -3, 13),
            Arguments.of("Aged Brie", -5, 49, -6, 50)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNoExpiryItemsAndExpectationsSuccess")
    void testUpdateQualityNoExpiry(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        applyAndAssert(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideNoExpiryItemsAndExpectationsSuccess() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("Sulfuras, Hand of Ragnaros", 3, 80, 3, 80),
            Arguments.of("Sulfuras, Hand of Ragnaros", 2, 80, 2, 80),
            Arguments.of("Sulfuras, Hand of Ragnaros", 0, 80, 0, 80),
            Arguments.of("Sulfuras, Hand of Ragnaros", -1, 80, -1, 80)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFinalExpiryItemsAndExpectationsSuccess2")
    void testUpdateQualityFinalExpiry(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        applyAndAssert(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideFinalExpiryItemsAndExpectationsSuccess2() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 15, 20, 14, 21),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 12, 20, 11, 21),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 11, 21, 10, 22),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 10, 22, 9, 24),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 9, 24, 8, 26),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 6, 20, 5, 22),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 5, 20, 4, 23),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 2, 20, 1, 23),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 1, 20, 0, 23),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 0, 20, -1, 0),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", -1, 20, -2, 0),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", -2, 20, -3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDoubleExpiryItemsAndExpectationsSuccess2")
    void testUpdateQualityDoubleExpiry(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        applyAndAssert(itemName, sellIn, quality, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideDoubleExpiryItemsAndExpectationsSuccess2() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("Conjured Mana Cake", 3, 12, 2, 10),
            Arguments.of("Conjured Mana Cake", 2, 10, 1, 8),
            Arguments.of("Conjured Mana Cake", 1, 8, 0, 6),
            Arguments.of("Conjured Mana Cake", 0, 6, -1, 2),
            Arguments.of("Conjured Mana Cake", -1, 2, -2, 0),
            Arguments.of("Conjured Mana Cake", -2, 0, -3, 0)
        );
    }

    private void applyAndAssert(String itemName, int sellIn, int quality, int expectedSellIn,
        int expectedQuality){
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Util.assertItem(app.items[0], itemName, expectedSellIn, expectedQuality);
    }
}
