package com.gildedrose.rules;

import com.gildedrose.model.Item;
import org.apache.commons.lang3.StringUtils;

public class NoExpiryDateRule extends AbstractRule {

    @Override
    public boolean apply(Item item) {
        itemsQualityRules.keySet()
            .stream()
            .filter(s -> StringUtils.startsWithIgnoreCase(item.name, s))
            .map(s -> itemsQualityRules.get(s))
            .findFirst()
            .filter(metadata -> metadata.getQualityRate().isEmpty())
            .ifPresent(metadata -> item.quality = metadata.getQualityValue());

        return applyNextRuleIfExist(item);
    }
}
