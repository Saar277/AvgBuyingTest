package org.example.mappers;

import org.example.StockPrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinalMapper {
    public static ArrayList<StockPrice> getStocksPriceToBuyList(List<StockPrice> stockPrices) {
        HashMap<String, List<StockPrice>> stocksInYears = YearMapper.getStocksPricesInYears(stockPrices);
        HashMap<String, HashMap<String, List<StockPrice>>> stocksInMonths = MonthMapper.getStocksPricesInMonth(stocksInYears);

        return DayMapper.getOneStockPriceForEveryMonth(stocksInMonths);
    }
}
