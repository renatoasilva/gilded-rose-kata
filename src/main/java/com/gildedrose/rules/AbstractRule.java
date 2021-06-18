package com.gildedrose.rules;

import com.gildedrose.model.Item;
import com.gildedrose.model.ItemQualityMetadata;
import com.gildedrose.repo.ItemRepository;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Abstract rule.
 */
public abstract class AbstractRule implements IRule {

    /**
     * A map containing the quality rules per item.
     */
    protected final Map<String, ItemQualityMetadata> itemsQualityRules = new ItemRepository().getItemsQualityMetadata();
    /**
     * The Next rule to be applied
     */
    protected IRule nextRule;

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

    protected ItemQualityMetadata getItemQualityMetadata(Item item){
        return itemsQualityRules.keySet()
            .stream()
            .filter(s -> StringUtils.startsWithIgnoreCase(item.name, s))
            .map(s -> itemsQualityRules.get(s))
            .findFirst()
            .orElse(itemsQualityRules.get(DEFAULT_ITEM));
    }

}
