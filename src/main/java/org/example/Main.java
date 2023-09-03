package org.example;

import org.example.finals.Finals;
import org.example.mappers.DayMapper;
import org.example.mappers.MonthMapper;
import org.example.mappers.YearMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String... args) {
        List<StockPrice> stockPrices = CSVReader.readStocksPriceFromCSV("/Users/snir/Downloads/SPY.csv");
        HashMap<String, List<StockPrice>> stocksInYears = YearMapper.getStocksPricesInYears(stockPrices);
        HashMap<String, HashMap<String, List<StockPrice>>> stocksInMonths = MonthMapper.getStocksPricesInMonth(stocksInYears);
        ArrayList<StockPrice> oneStockPriceForEveryMonth = DayMapper.getOneStockPriceForEveryMonth(stocksInMonths);
        AtomicReference<Double> priceSum = new AtomicReference<>((double) 0);

        oneStockPriceForEveryMonth.forEach((stockPrice -> {
            priceSum.updateAndGet(v -> new Double((double) (v + stockPrice.getPrice())));
        }));

        double avgCost = priceSum.get() / oneStockPriceForEveryMonth.size();
        double lastPrice = stockPrices.get(stockPrices.size() - 1).getPrice();
        double profitPercent = Utils.calcPercent(avgCost, lastPrice);
        int yearsCount = Utils.countYears(Finals.START_YEAR, Finals.END_YEAR);
        double avgYearProfitPercent = profitPercent / yearsCount;

        System.out.println("avgCost: " + avgCost);
        System.out.println("lastPrice: " + lastPrice);
        System.out.println("profitPercent: " + profitPercent);
        System.out.println("avg year profit %: " + avgYearProfitPercent);
    }
}