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

class NoExpiryDateRuleTest {

    private NoExpiryDateRule underTest;

    @BeforeEach
    void setUp() {
        underTest = new NoExpiryDateRule();
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
            Arguments.of("StandardItem", 3, 12, 3, 12),
            Arguments.of("StandardItem", 1, 10, 1, 10),
            Arguments.of("StandardItem", 0, 10, 0, 10),
            Arguments.of("StandardItem", -1, 10, -1, 10),
            Arguments.of("Sulfuras", 1, 10, 1, 80), //Sulfuras quality is always 80
            Arguments.of("Sulfuras", 0, 10, 0, 80),
            Arguments.of("Sulfuras", -1, 10, -1, 80),
            Arguments.of("Sulfuras", -2, 10, -2, 80)

        );
    }
}
