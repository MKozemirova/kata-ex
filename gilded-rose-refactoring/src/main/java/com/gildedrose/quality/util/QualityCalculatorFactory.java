package com.gildedrose.quality.util;

import com.gildedrose.quality.AgedBrieQualityCalculator;
import com.gildedrose.quality.BackstagePassesQualityCalculator;
import com.gildedrose.quality.DefaultQualityCalculator;
import com.gildedrose.quality.QualityCalculator;
import com.gildedrose.quality.SulfurasQualityCalculator;
import java.util.List;

/**
 * TODO:
 * + 1) Create a list of all managers except DefaultItemManager and iterate to get an instance
 * + 2) If no instance -> default
 * 3) Try switch expression to not forget classes
 */
public class QualityCalculatorFactory {

    private static QualityCalculatorFactory instance;
    private final List<QualityCalculator> qualityCalculators;
    private final DefaultQualityCalculator defaultQC;

    private QualityCalculatorFactory() {
        qualityCalculators = List.of(
                new AgedBrieQualityCalculator(),
                new BackstagePassesQualityCalculator(),
                new SulfurasQualityCalculator());
        defaultQC = new DefaultQualityCalculator()
    }

    public static QualityCalculatorFactory getInstance() {
        if (instance == null) {
            instance = new QualityCalculatorFactory();
        }
        return instance;
    }

    public QualityCalculator getCalculator(String itemName) {
        return qualityCalculators.stream()
                .filter(manager -> manager.match(itemName))
                .findAny()
                .orElse(defaultQC);
    }
}
