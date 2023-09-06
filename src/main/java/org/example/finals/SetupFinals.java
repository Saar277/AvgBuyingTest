package org.example.finals;

import org.example.StockPrice;
import org.example.Utils;

import java.util.List;

public class SetupFinals {
    public static void setup(List<StockPrice> stockPrices) {
        StockPrice first = stockPrices.get(0);
        Finals.setStartYear(Utils.getYearFromStockPrice(first));
        Finals.setStartMonth(Utils.getMonthFromStockPrice(first));

        StockPrice last = stockPrices.get(stockPrices.size() - 1);
        Finals.setEndYear(Utils.getYearFromStockPrice(last));
        Finals.setEndMonth(Utils.getMonthFromStockPrice(last));
        Finals.setLastPrice(last.getPrice());
    }
}
