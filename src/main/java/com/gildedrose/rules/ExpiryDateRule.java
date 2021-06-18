package com.gildedrose.rules;

import com.gildedrose.model.Item;
import com.gildedrose.model.ItemQualityMetadata;
import org.apache.commons.lang3.StringUtils;

public class ExpiryDateRule extends AbstractRule {

    @Override
    public boolean apply(Item item) {
        if (!isApplicable()) {
            return applyNextRuleIfExist(item);
        }

        int newQuality = applyRule(item);
        newQuality = Math.max(MIN_QUALITY, newQuality);
        item.quality = newQuality;

        return applyNextRuleIfExist(item);
    }

    private boolean isApplicable() {
        // in case there is a more specific case, this method would define whether to run the rule or not
        return true;
    }

    protected int applyRule(Item item) {
        final ItemQualityMetadata itemQualityMetadata = itemsQualityRules.keySet()
            .stream()
            .filter(s -> StringUtils.startsWithIgnoreCase(item.name, s))
            .map(s -> itemsQualityRules.get(s))
            .findFirst()
            .orElse(itemsQualityRules.get(DEFAULT_ITEM));

        int newQuality = itemQualityMetadata.getQualityRate()
            .keySet()
            .stream()
            .filter(r -> r.contains(item.sellIn))
            .map(r -> itemQualityMetadata.getQualityRate().get(r))
            .findFirst()
            .map(rate -> item.quality + rate)
            .orElse(itemQualityMetadata.getQualityValue());

        return Math.min(itemQualityMetadata.getMaxQuality(), newQuality);
    }


}
