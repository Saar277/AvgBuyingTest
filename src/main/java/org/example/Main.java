package org.example;

import org.example.stocks.StockTester;
import org.example.userio.UserIOInstance;

public class Main {
    public static void main(String[] args) {
        String resourcesPath = "src/main/resources";

        UserIOInstance.getInstance().print("SPY TEST:");
        StockTester.testStock(CSVReader.getStocksPriceFromCSV(resourcesPath + "/SPY.csv"));

        UserIOInstance.getInstance().print("QQQ TEST:");
        StockTester.testStock(CSVReader.getStocksPriceFromCSV(resourcesPath + "/QQQ.csv"));
    }
}