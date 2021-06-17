package com.gildedrose.model;

import java.util.Map;
import org.apache.commons.lang3.Range;

public class ItemQualityMetadata {

    Map<Range, Integer> qualityRate;
    Integer qualityValue = null;

    public Map<Range, Integer> getQualityRate() {
        return qualityRate;
    }

    public Integer getQualityValue() {
        return qualityValue;
    }

    public ItemQualityMetadata(Map<Range, Integer> qualityRate, int qualityValue) {
        this.qualityRate = qualityRate;
        this.qualityValue = qualityValue;
    }

    public ItemQualityMetadata(Map<Range, Integer> qualityRate) {
        this.qualityRate = qualityRate;
    }
}
