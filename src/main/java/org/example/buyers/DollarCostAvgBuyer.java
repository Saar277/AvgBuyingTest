package org.example.buyers;

import org.example.StockPrice;
import org.example.Utils;
import org.example.finals.Finals;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class DollarCostAvgBuyer {
    public static double getAvgAnnualReturn(ArrayList<StockPrice> oneStockPriceForEveryMonthList) {
        int moneyAmountForEveryBuy = 1000;
        AtomicReference<Double> stocksAmount = new AtomicReference<>((double) 0);

        oneStockPriceForEveryMonthList.forEach((stockPrice -> {
            stocksAmount.updateAndGet(value -> new Double
                    ((value + (moneyAmountForEveryBuy / stockPrice.getPrice()))));
        }));

        int moneyAmountInvested = moneyAmountForEveryBuy * oneStockPriceForEveryMonthList.size();
        double currPositionMoneyAmount = stocksAmount.get() * Finals.LAST_PRICE;
        double profitPercent = Utils.calcPercent(moneyAmountInvested, currPositionMoneyAmount);
        int yearsCount = Utils.countYears(Finals.START_YEAR, Finals.END_YEAR);
        double avgAnnualReturn = profitPercent / yearsCount;

        return avgAnnualReturn;
    }
}
