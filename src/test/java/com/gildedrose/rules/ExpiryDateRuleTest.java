package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Util;
import com.gildedrose.model.Item;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ExpiryDateRuleTest {

    private ExpiryDateRule underTest;

    @BeforeEach
    void setUp() {
        underTest = new ExpiryDateRule();
    }

    @ParameterizedTest
    @MethodSource("provideItemsAndExpectations")
    void testApply(String itemName, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item item = new Item(itemName, sellIn, quality);
        underTest.apply(item);
        Util.assertItem(item, itemName, expectedSellIn, expectedQuality);
    }

    private static Stream<Arguments> provideItemsAndExpectations() {
        //item name, sellIn, quality, expectedSellIn, expectedQuality
        return Stream.of(
            Arguments.of("StandardItem", 2, 5, 2, 4), //decrease quality with time
            Arguments.of("StandardItem", 1, 4, 1, 3),
            Arguments.of("StandardItem", 0, 3, 0, 1), //quality decreases 2xfaster when sellIn <=0
            Arguments.of("StandardItem", -1, 2, -1, 0),
            Arguments.of("StandardItem", -2, 1, -2, 0),
            Arguments.of("StandardItem", -3, 0, -3, 0),

            Arguments.of("Aged Brie", 2, 2, 2, 3), //increase in quality with time
            Arguments.of("Aged Brie", 1, 3, 1, 4),
            Arguments.of("Aged Brie", 0, 4, 0, 6), //double increase in quality for sellIn <=0
            Arguments.of("Aged Brie", -1, 6, -1, 8),

            Arguments.of("Sulfuras", 0, 10, 0, 80),
            Arguments.of("Sulfuras", -1, 10, -1, 80),
            Arguments.of("Sulfuras", -2, 10, -2, 80),

            Arguments.of("Backstage passes", 15, 20, 15, 21), //item quality increases in time
            Arguments.of("Backstage passes", 14, 21, 14, 22),
            Arguments.of("Backstage passes", 11, 22, 11, 23),
            Arguments.of("Backstage passes", 10, 23, 10, 25), //item quality increases 2x if sellIn <= 5
            Arguments.of("Backstage passes", 6, 22, 6, 24),
            Arguments.of("Backstage passes", 5, 24, 5, 27), //item quality increases 3x if sellIn <= 5
            Arguments.of("Backstage passes", 2, 27, 2, 30),
            Arguments.of("Backstage passes", 1, 30, 1, 33),
            Arguments.of("Backstage passes", 0, 33, 0, 0), //item quality is 0 if sellIn <= 0
            Arguments.of("Backstage passes", -1, 20, -1, 0),
            Arguments.of("Backstage passes", -2, 20, -2, 0),

            Arguments.of("Conjured Mana Cake", 3, 12, 3, 10), //item quality decreases 2xfaster
            Arguments.of("Conjured Mana Cake", 2, 10, 2, 8),
            Arguments.of("Conjured Mana Cake", 1, 8, 1, 6),
            Arguments.of("Conjured Mana Cake", 0, 8, 0, 4), //item quality decreases 4xfaster
            Arguments.of("Conjured Mana Cake", -1, 4, -1, 0),
            Arguments.of("Conjured Mana Cake", -2, 0, -2, 0)
        );
    }
}
