package com.gildedrose.repo;

import com.gildedrose.model.ItemQualityMetadata;
import com.gildedrose.rules.IRule;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Range;

public class ItemRepository {

    private final Map<String, ItemQualityMetadata> itemsQualityMetadata;

    public ItemRepository() {
        this.itemsQualityMetadata = buildItemQualityMetadata();
    }

    public Map<String, ItemQualityMetadata> getItemsQualityMetadata() {
        return itemsQualityMetadata;
    }

    /**
     * Defines the quality metadata per item
     *
     * @return a map with key the item description (or prefix) and with value the quality rules to
     * be applied.
     */
    private Map<String, ItemQualityMetadata> buildItemQualityMetadata(){
        Map<String, ItemQualityMetadata> itemsMetadata = new HashMap<>();
        Map<Range, Integer> expiryRate = new HashMap<>();
        expiryRate.put(Range.between(1, Integer.MAX_VALUE), -1);
        expiryRate.put(Range.between(Integer.MIN_VALUE, 0), -2);
        itemsMetadata.put(IRule.DEFAULT_ITEM, new ItemQualityMetadata(expiryRate));

        Map<Range, Integer> expiryRateAgeBrie = new HashMap<>();
        expiryRateAgeBrie.put(Range.between(1, Integer.MAX_VALUE), 1);
        expiryRateAgeBrie.put(Range.between(Integer.MIN_VALUE, 0), 2);
        itemsMetadata.put("Aged Brie", new ItemQualityMetadata(expiryRateAgeBrie));

        Map<Range, Integer> expiryRateSulfuras = new HashMap<>();
        expiryRateSulfuras.put(Range.between(Integer.MIN_VALUE, Integer.MAX_VALUE), 80);
        itemsMetadata.put("Sulfuras", new ItemQualityMetadata(expiryRateSulfuras, 80, 80, false));

        Map<Range, Integer> expiryRateBackstage = new HashMap<>();
        expiryRateBackstage.put(Range.between(11, Integer.MAX_VALUE), 1);
        expiryRateBackstage.put(Range.between(6, 10), 2);
        expiryRateBackstage.put(Range.between(1, 5), 3);
        itemsMetadata.put("Backstage passes", new ItemQualityMetadata(expiryRateBackstage, 0));

        Map<Range, Integer>  expiryRateConjured = new HashMap<>();
        expiryRateConjured.put(Range.between(1, Integer.MAX_VALUE), -2);
        expiryRateConjured.put(Range.between(Integer.MIN_VALUE, 0), -4);
        itemsMetadata.put("Conjured", new ItemQualityMetadata(expiryRateConjured));

        return itemsMetadata;
    }



}
