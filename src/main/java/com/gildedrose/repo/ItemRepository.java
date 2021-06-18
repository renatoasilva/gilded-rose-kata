package com.gildedrose.repo;

import com.gildedrose.model.ItemQualityMetadata;
import com.gildedrose.rules.IRule;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Range;

public class ItemRepository {

    private final Map<String, ItemQualityMetadata> itemsQualityRules;

    public ItemRepository() {
        this.itemsQualityRules = buildItemQualityRules();
    }

    public Map<String, ItemQualityMetadata> getItemsQualityRules() {
        return itemsQualityRules;
    }

    /**
     * Builds quality rules per item
     *
     * @return a map with key the item description (or prefix) and with value the quality rules to
     * be applied.
     */
    private Map<String, ItemQualityMetadata> buildItemQualityRules(){
        Map<String, ItemQualityMetadata> itemsMetadata = new HashMap<>();
        Map<Range, Integer> expiryRate = new HashMap<>();
        expiryRate.put(Range.between(1, Integer.MAX_VALUE), -1);
        expiryRate.put(Range.between(Integer.MIN_VALUE, 0), -2);
        itemsMetadata.put(IRule.DEFAULT_ITEM, new ItemQualityMetadata(expiryRate));

        Map<Range, Integer> expiryRateAgeBrie = new HashMap<>();
        expiryRateAgeBrie.put(Range.between(1, Integer.MAX_VALUE), 1);
        expiryRateAgeBrie.put(Range.between(Integer.MIN_VALUE, 0), 2);
        itemsMetadata.put("Aged Brie", new ItemQualityMetadata(expiryRateAgeBrie));

        expiryRate = new HashMap<>();
        itemsMetadata.put("Sulfuras", new ItemQualityMetadata(expiryRate, 80, 80, false));

        expiryRate = new HashMap<>();
        expiryRate.put(Range.between(11, Integer.MAX_VALUE), 1);
        expiryRate.put(Range.between(6, 10), 2);
        expiryRate.put(Range.between(1, 5), 3);
        itemsMetadata.put("Backstage passes", new ItemQualityMetadata(expiryRate, 0));

        expiryRate = new HashMap<>();
        expiryRate.put(Range.between(1, Integer.MAX_VALUE), -2);
        expiryRate.put(Range.between(Integer.MIN_VALUE, 0), -4);
        itemsMetadata.put("Conjured", new ItemQualityMetadata(expiryRate));

        return itemsMetadata;
    }



}
