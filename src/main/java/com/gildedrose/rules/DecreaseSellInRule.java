package com.gildedrose.rules;

import com.gildedrose.model.Item;
import com.gildedrose.model.ItemQualityMetadata;
import org.apache.commons.lang3.StringUtils;

public class DecreaseSellInRule extends AbstractRule {

    @Override
    public boolean apply(Item item) {
        final ItemQualityMetadata itemQualityMetadata = itemsQualityRules.keySet()
            .stream()
            .filter(s -> StringUtils.startsWithIgnoreCase(item.name, s))
            .map(s -> itemsQualityRules.get(s))
            .findFirst()
            .orElse(itemsQualityRules.get(DEFAULT_ITEM));

        if(itemQualityMetadata.isQualityVariable()){
            item.sellIn = item.sellIn - 1;
        }

        return applyNextRuleIfExist(item);
    }
}
