package com.gildedrose.rules;

import com.gildedrose.model.Item;

public interface IRule {

    int MIN_QUALITY = 0;
    int MAX_QUALITY = 50;
    String DEFAULT_ITEM = "DEFAULT_ITEM";


    /**
     * Sets the next rule to be applied
     */
    void setNextRule(IRule nextRule);

    /**
     * Apply the rules agains the received item.
     *
     * @param item the item
     * @return whether the rule was applied or not
     */
    boolean apply(Item item);


}
