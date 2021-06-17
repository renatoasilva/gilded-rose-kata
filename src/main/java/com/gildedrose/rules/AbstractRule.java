package com.gildedrose.rules;

import com.gildedrose.model.Item;
import com.gildedrose.model.ItemQualityMetadata;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Range;

/**
 * The type Abstract rule.
 */
public abstract class AbstractRule implements IRule {

    /**
     * The Next rule to be applied
     */
    protected IRule nextRule;
    /**
     * A map containing the quality rules per item.
     */
    protected final Map<String, ItemQualityMetadata> itemsQualityRules = buildItemQualityRules();

    public void setNextRule(IRule nextRule) {
        this.nextRule = nextRule;
    }

    /**
     * Apply next rule if exist boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean applyNextRuleIfExist(Item item) {
        if (this.nextRule != null) {
            return this.nextRule.apply(item);
        }
        return false;
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
        itemsMetadata.put(DEFAULT_ITEM, new ItemQualityMetadata(expiryRate));

        expiryRate = new HashMap<>();
        expiryRate.put(Range.between(1, Integer.MAX_VALUE), 1);
        expiryRate.put(Range.between(Integer.MIN_VALUE, 0), 2);
        itemsMetadata.put("Aged Brie", new ItemQualityMetadata(expiryRate));

        expiryRate = new HashMap<>();
        itemsMetadata.put("Sulfuras", new ItemQualityMetadata(expiryRate, 80));

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
