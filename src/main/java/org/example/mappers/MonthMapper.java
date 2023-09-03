package org.example.mappers;

import org.example.StockPrice;
import org.example.Utils;
import org.example.finals.Finals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonthMapper {
    public static HashMap<String, HashMap<String, List<StockPrice>>> getStocksPricesInMonth(HashMap<String, List<StockPrice>> stocksPricesInYears) {
        HashMap<String, HashMap<String, List<StockPrice>>> stocksInMonthForEveryYear = new HashMap<>();

        stocksPricesInYears.forEach((year, stocks) -> {
            List<StockPrice> stocksPrices = new ArrayList<>(stocks);
            ArrayList<String> months = getMonths(year);
            HashMap<String, List<StockPrice>> stocksInMonths = new HashMap<>();

            for (String month : months) {
                ArrayList<Integer> monthStartAndEndIndex = getMonthStartAndEndIndex(month, stocksPrices);
                int monthStartIndex = monthStartAndEndIndex.get(0);
                int monthEndIndex = monthStartAndEndIndex.get(1);

                List<StockPrice> currMonthStocks = stocksPrices.subList(monthStartIndex, monthEndIndex + 1);
                stocksInMonths.put(month, currMonthStocks);

                stocksPrices = Utils.getUpdatedStockPriceList(stocksPrices, monthEndIndex);
            }
            stocksInMonthForEveryYear.put(year, stocksInMonths);
        });

        return stocksInMonthForEveryYear;
    }

    public static ArrayList<Integer> getMonthStartAndEndIndex(String month, List<StockPrice> stockPrices) {
        if (month.equals("12")) {
            return getMonthStartAndEndIndexForLastMonth(stockPrices);
        } else {
            ArrayList<Integer> counts = new ArrayList<>();
            int count = 0;

            for (StockPrice stockPrice : stockPrices) {
                int index = stockPrice.getDate().indexOf("-");
                String monthFromDate = stockPrice.getDate().substring((index + 1), (index + 3));

                if (month.equals(monthFromDate)) {
                    if (counts.size() == 1) {
                        int endIndex = count - 1;
                        counts.add(endIndex);

                        return counts;
                    } else {
                        counts.add(count);

                        if (Utils.checkIfItsEndMonthAndEndYear(stockPrices, month)) {
                            counts.add(stockPrices.size() - 1);

                            return counts;
                        } else {
                            month = getNextMonthInString(Integer.parseInt(month));
                        }
                    }
                }

                count++;
            }
        }

        return null;
    }

    public static ArrayList<Integer> getMonthStartAndEndIndexForLastMonth(List<StockPrice> stockPrices) {
        String month = "12";
        ArrayList<Integer> counts = new ArrayList<>();
        int count = 0;

        for (StockPrice stockPrice : stockPrices) {
            int index = stockPrice.getDate().indexOf("-");
            String monthFromDate = stockPrice.getDate().substring((index + 1), (index + 3));

            if (month.equals(monthFromDate)) {
                counts.add(count);
                counts.add(stockPrices.size() - 1);

                return counts;
            }

            count++;
        }

        return counts;
    }

    public static String getNextMonthInString(int month) {
        if (month < 9) {
            return "0" + (month + 1);
        } else {
            return String.valueOf(month + 1);
        }
    }

    public static ArrayList<String> getMonths(String year) {
        if (Integer.parseInt(year) == Finals.END_YEAR) {
            return Utils.getMonthsInArrayForEndYear();
        } else if (Integer.parseInt(year) == Finals.START_YEAR) {
            return Utils.getMonthsInArrayForStartYear();
        } else {
            return Utils.getMonthsInArray();
        }
    }
}
