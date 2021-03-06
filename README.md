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
applied to items. To make that more explicit, an interface
[IRule](src/main/java/com/gildedrose/rules/IRule.java) was created
containing two method:
- `apply`: applies the rule
- `setNextRule`: sets which rule to apply next.

Note that the order in which the rules are run are important as affects the output.

### Design pattern

We have a set of rules for each item. Therefore a useful design pattern to be used would be
chain of responsibilites where each item will a set of the rules applied
in a sequential order.

Rules, defined in method [createRuleChain](src/main/java/com/gildedrose/GildedRose.java) are:
- ExpiryDateRule
- DecreaseSellInRule
- NoExpiryDateRule

The definition of the rules per item is defined in [buildItemQualityRules](src/main/java/com/gildedrose/rules/AbstractRule.java)
where the quality metadata per item is stored in a Map with `key` as Range (in days) and `value` the quality rate value to be applied.
A fixed qualityValue is set (instead of a rate) if:
- items that don't have an expiry date.
- range available for item cannot be matched.

There is a default rule with key `com.gildedrose.rules.IRule.DEFAULT_ITEM` that is applied when the item name cannot be matched.

Whenever a rule item is to be added, the method `buildItemQualityRules` needs to be updated to correctly reflect the rules.

## Tests

To ensure that the new built logic is correct for the existing items,
the tests in [GildedRoseTest.java](src/test/java/com/gildedrose/GildedRoseTest.java)
were extended to cover all the possible kinds of items.

### Testing rules against items

I have decided to test the rules of the items itself on the unit tests. More tests
could have been added to ensure the sanity of the ranges like ensure that the ranges
within the same rule, do not overlap, but due to lack of time it was not done.

### Coverage

Currently the tests cover around 97% for line and 94% for method coverages.

## Considerations

Using a "name" as a key is not ideal, since it is prone to errors:
- duplication
- case sensitive.

The best solution would be to categorise the items and use that category to define which rules to apply.
Or use some kind of id.
