package com.gildedrose.model;

import java.util.Map;
import org.apache.commons.lang3.Range;

public class ItemQualityMetadata {

    Map<Range, Integer> qualityRate;
    Integer qualityValue = null;
    Integer maxQuality = 50;

    public boolean isQualityVariable() {
        return isQualityVariable;
    }

    boolean isQualityVariable = true;

    public Map<Range, Integer> getQualityRate() {
        return qualityRate;
    }

    public Integer getQualityValue() {
        return qualityValue;
    }

    public Integer getMaxQuality() {
        return maxQuality;
    }

    public ItemQualityMetadata(Map<Range, Integer> qualityRate, int qualityValue, int  maxQuality, boolean isQualityVariable) {
        this.qualityRate = qualityRate;
        this.qualityValue = qualityValue;
        this.maxQuality = maxQuality;
        this.isQualityVariable = isQualityVariable;
    }

    public ItemQualityMetadata(Map<Range, Integer> qualityRate, int qualityValue) {
        this.qualityRate = qualityRate;
        this.qualityValue = qualityValue;
    }

    public ItemQualityMetadata(Map<Range, Integer> qualityRate) {
        this.qualityRate = qualityRate;
    }
}
