package com.gildedrose.rules;

import com.gildedrose.model.Item;
import org.apache.commons.lang3.StringUtils;

public class DecreaseSellInRule extends AbstractRule {

    @Override
    public boolean apply(Item item) {
        if (!StringUtils.startsWithIgnoreCase(item.name, "Sulfuras")) {
            item.sellIn = item.sellIn - 1;
        }
        return applyNextRuleIfExist(item);
    }
}
