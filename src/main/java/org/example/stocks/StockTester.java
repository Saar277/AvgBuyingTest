package org.example.stocks;

import org.example.StockPrice;
import org.example.buyers.DollarCostAvgBuyer;
import org.example.buyers.StockAvgBuyer;
import org.example.finals.SetupFinals;
import org.example.mappers.FinalMapper;
import org.example.userio.UserIOInstance;

import java.util.ArrayList;
import java.util.List;

public class StockTester {
    public static void testStock(List<StockPrice> stockPrices) {
        SetupFinals.setup(stockPrices);
        ArrayList<StockPrice> oneStockPriceForEveryMonth = FinalMapper.getStocksPriceToBuyList(stockPrices);

        double stockAvgBuyerAnnualReturn = StockAvgBuyer.getAvgAnnualReturn(oneStockPriceForEveryMonth);
        double dollarCostAvgBuyerAnnualReturn = DollarCostAvgBuyer.getAvgAnnualReturn(oneStockPriceForEveryMonth);

        UserIOInstance.getInstance().print("stock avg buyer annual return: " + stockAvgBuyerAnnualReturn + "%");
        UserIOInstance.getInstance().print("dollar cost avg buyer annual return: " + dollarCostAvgBuyerAnnualReturn + "%");

    }
}
