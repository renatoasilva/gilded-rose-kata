package com.gildedrose.rules;

import com.gildedrose.model.Item;
import com.gildedrose.model.ItemQualityMetadata;
import org.apache.commons.lang3.StringUtils;

public class DecreaseSellInRule extends AbstractRule {

    @Override
    public boolean apply(Item item) {
        final ItemQualityMetadata itemQualityMetadata = getItemQualityMetadata(item);

        if(itemQualityMetadata.isQualityVariable()){
            item.sellIn = item.sellIn - 1;
        }

        return applyNextRuleIfExist(item);
    }
}
