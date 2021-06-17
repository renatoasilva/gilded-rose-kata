# Requirements

Requirements: https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements.txt

## Build tool

Standalone command-line application built using:

* Maven
* Java 8
* Git

## Implementation

The original implementation has the logic in [GildedRose.java](src/main/java/com/gildedrose/GildedRose.java).
The logic has many if/else statements which:
- makes it hard to read
- makes it hard to add new items
- make it hard to separate concerns and common patterns in items

To improve the current implementation, the first step is to identify patterns in
the logic and reuse them. The requirement describes a set of rules that need to be
applied to items. To make that more explicit, create an interface
[IRule](src/main/java/com/gildedrose/rules/IRule.java)
containing two method: one that applies the rule and the second to know
which rule to apply next.

Note that the order in which the rules are applied are important as it
affects the output.

### Design pattern

We have a set of rules that need to be applied for each item. Therefore a useful design pattern
to be used would be chain of responsibilites where each item will a set of the rules applied
in a sequential order.

Rules, defined in method [createRuleChain](src/main/java/com/gildedrose/GildedRose.java) are:
- ExpiryDateRule
- DecreaseSellInRule
- NoExpiryDateRule

The definition of the rules per item is defined in [buildItemQualityRules](src/main/java/com/gildedrose/rules/AbstractRule.java)
where the metadata per item is stored in Map with key as a Range (in days) and the value the quality rate value to be applied.
A fixed qualityValue is set (instead of a rate) is applied to:
- items that don't have an expiry date.
- items where the range does not match the definition.

There is a default rule with key `com.gildedrose.rules.IRule.DEFAULT_ITEM` that is applied when the item name cannot be matched.

Whenever a rule item is to be added, the method `buildItemQualityRules` needs to be updated to correctly reflect the rules.

## Tests

To ensure that the new built logic is correct for the existing items,
the tests in [GildedRoseTest.java](src/test/java/com/gildedrose/GildedRoseTest.java)
were extended to cover all the possible kinds of items.

## Considerations

Using a "name" as a key is not ideal, since it is prone to errors:
- duplication
- case sensitive.

The best solution would be to categorise the items and use that category to define which rules to apply.
Or use some kind of id.
