package com.gildedrose.quality;

import java.util.List;

/**
 * TODO:
 * + 1) Create a list of all managers except DefaultItemManager and iterate to get an instance
 * + 2) If no instance -> default
 * 3) Try switch expression to not forget classes
 */
public class QualityManagerFactory {

    private static final List<QualityCalculator> QUALITY_CALCULATORS = List.of(
            new AgedBrieQualityCalculator(),
            new BackstagePassesQualityCalculator(),
            new SulfurasQualityCalculator());

    private static final DefaultQualityCalculator defaultQM = new DefaultQualityCalculator();


    public static QualityCalculator getManager(String itemName) {

        return QUALITY_CALCULATORS.stream()
                .filter(manager -> manager.match(itemName))
                .findAny()
                .orElse(defaultQM);
    }
}
