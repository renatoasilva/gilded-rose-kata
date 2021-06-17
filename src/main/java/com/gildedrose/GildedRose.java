package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.rules.DecreaseSellInRule;
import com.gildedrose.rules.ExpiryDateRule;
import com.gildedrose.rules.IRule;
import com.gildedrose.rules.NoExpiryDateRule;

class GildedRose {

    private final IRule initialRule;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        initialRule = createChainOfRules();
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            initialRule.apply(items[i]);
        }
    }

    /**
     * Setup the chain of rules to be applied
     *
     * @return the initial rule
     */
    private IRule createChainOfRules() {
        DecreaseSellInRule decreaseSellIn = new DecreaseSellInRule();
        NoExpiryDateRule noExpiryDateRule = new NoExpiryDateRule();
        ExpiryDateRule expiryDateRule = new ExpiryDateRule();

        expiryDateRule.setNextRule(decreaseSellIn);
        decreaseSellIn.setNextRule(noExpiryDateRule);

        return expiryDateRule;
    }

//    Item[] items;
//
//    public GildedRose(Item[] items) {
//        this.items = items;
//    }
//
//    @Override
//    public void updateQuality() {
//        for (int i = 0; i < items.length; i++) {
//            if (!items[i].name.equals("Aged Brie")
//                && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                if (items[i].quality > 0) {
//                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                        items[i].quality = items[i].quality - 1;
//                    }
//                }
//            } else {
//                if (items[i].quality < 50) {
//                    items[i].quality = items[i].quality + 1;
//
//                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].sellIn < 11) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//
//                        if (items[i].sellIn < 6) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (items[i].name.equals("Conjured Mana Cake")) {
//                items[i].quality = items[i].quality - 1;
//            }
//
//            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                items[i].sellIn = items[i].sellIn - 1;
//            }
//
//            if (items[i].sellIn < 0) {
//                if (!items[i].name.equals("Aged Brie")) {
//                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].quality > 0) {
//                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                                items[i].quality = items[i].quality - 1;
//                            }
//                        }
//                    } else {
//                        items[i].quality = items[i].quality - items[i].quality;
//                    }
//                } else {
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1;
//                    }
//                }
//            }
//        }
//    }


}