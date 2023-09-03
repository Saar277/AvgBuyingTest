package org.example.mappers;

import org.example.StockPrice;
import org.example.Utils;
import org.example.finals.Finals;

import java.util.HashMap;
import java.util.List;

public class YearMapper {
    public static HashMap<String, List<StockPrice>> getStocksPricesInYears(List<StockPrice> stockPrices) {
        HashMap<String, List<StockPrice>> stocksInYears = new HashMap<>();

        for (String year : Utils.getYearsInArray(Finals.START_YEAR, Finals.END_YEAR)) {
            int startIndex = getYearIndex(stockPrices, year);
            int endIndex = getYearIndex(stockPrices, String.valueOf(Integer.parseInt(year) + 1));
            stocksInYears.put(year, stockPrices.subList(startIndex, endIndex));
            stockPrices = Utils.getUpdatedStockPriceList(stockPrices, endIndex);
        }

        return stocksInYears;
    }

    public static int getYearIndex(List<StockPrice> stockPrices, String year) {
        if (Integer.parseInt(year) == (Finals.END_YEAR + 1)) {
            return stockPrices.size();
        } else {
            int count = 0;

            for (StockPrice stockPrice : stockPrices) {
                if (stockPrice.getDate().contains(year)) {
                    return count;
                }

                count++;
            }
        }

        return 0;
    }
}
