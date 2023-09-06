package org.example.buyers;

import org.example.StockPrice;
import org.example.Utils;
import org.example.finals.Finals;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class StockAvgBuyer {
    public static double getAvgAnnualReturn(ArrayList<StockPrice> oneStockPriceForEveryMonthList) {
        AtomicReference<Double> priceSum = new AtomicReference<>((double) 0);

        oneStockPriceForEveryMonthList.forEach((stockPrice -> {
            priceSum.updateAndGet(v -> new Double((v + stockPrice.getPrice())));
        }));

        double avgCost = priceSum.get() / oneStockPriceForEveryMonthList.size();
        double profitPercent = Utils.calcPercent(avgCost, Finals.LAST_PRICE);
        int yearsCount = Utils.countYears(Finals.START_YEAR, Finals.END_YEAR);
        double avgAnnualReturn = profitPercent / yearsCount;

        return avgAnnualReturn;
    }
}
