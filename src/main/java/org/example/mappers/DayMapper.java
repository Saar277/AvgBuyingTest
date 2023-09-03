package org.example.mappers;

import org.example.StockPrice;
import org.example.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DayMapper {
    public static ArrayList<StockPrice> getOneStockPriceForEveryMonth(HashMap<String, HashMap<String, List<StockPrice>>> stocksInMonths) {
        ArrayList<StockPrice> oneStockPriceForEveryMonth = new ArrayList<>();

        stocksInMonths.forEach((year, months) -> months.forEach((month, stocks) -> {
            AtomicBoolean isStockInsertIntoArray = new AtomicBoolean(false);
            stocks.forEach((stockPrice -> {
                if (Utils.getDayFromStockPrice(stockPrice) == 11 && !isStockInsertIntoArray.get()) {
                    oneStockPriceForEveryMonth.add(stockPrice);
                    isStockInsertIntoArray.set(true);
                } else if (Utils.getDayFromStockPrice(stockPrice) >= 12 && !isStockInsertIntoArray.get()) {
                    oneStockPriceForEveryMonth.add(stockPrice);
                    isStockInsertIntoArray.set(true);
                }
            }));
        }));

        return oneStockPriceForEveryMonth;
    }
}
