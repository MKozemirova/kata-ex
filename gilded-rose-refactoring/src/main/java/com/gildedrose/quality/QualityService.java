package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.util.QualityCalculatorFactory;

public class QualityService {

    private final QualityCalculatorFactory calculatorFactory;

    public QualityService(QualityCalculatorFactory calculatorFactory) {
        this.calculatorFactory = calculatorFactory;
    }

    public int calculateDelta(Item item) {
        QualityCalculator qualityCalculator = calculatorFactory.getCalculator((item.name);
        int initialDelta = qualityCalculator.calculateDelta(item.quality, item.sellIn);

        return item.name.contains("Conjured") ? initialDelta*2 : initialDelta;
    }
}
