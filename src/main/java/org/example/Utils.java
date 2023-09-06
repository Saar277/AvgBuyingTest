package org.example;

import org.example.finals.Finals;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static int countYears(int startYear, int endYear) {
        int count = 0;
        for (int i = startYear; i <= endYear; i++) {
            count++;
        }

        return count;
    }

    public static int countMonth(int years, int currMonth) {
        int count = (years - 1) * 12;
        count += currMonth;

        return count;
    }

    public static ArrayList<String> getYearsInArray(int startYear, int endYear) {
        ArrayList<String> years = new ArrayList();
        for (int i = startYear; i <= endYear; i++) {
            years.add(String.valueOf(i));
        }

        return years;
    }

    public static ArrayList<String> getMonthsInArray() {
        ArrayList<String> months = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(String.valueOf(i));
            }
        }

        return months;
    }

    public static ArrayList<String> getMonthsInArrayForEndYear() {
        ArrayList<String> months = new ArrayList();
        for (int i = 1; i <= Finals.END_MONTH; i++) {
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(String.valueOf(i));
            }
        }

        return months;
    }

    public static ArrayList<String> getMonthsInArrayForStartYear() {
        ArrayList<String> months = new ArrayList();
        for (int i = Finals.START_MONTH; i <= 12; i++) {
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(String.valueOf(i));
            }
        }

        return months;
    }

    public static List<StockPrice> getUpdatedStockPriceList(List<StockPrice> stockPricesList, int endIndex) {
        if ((endIndex + 1) > stockPricesList.size()) {
            return stockPricesList.subList(endIndex, stockPricesList.size());
        } else {
            return stockPricesList.subList((endIndex + 1), stockPricesList.size());
        }
    }

    public static boolean checkIfItsEndYear(List<StockPrice> stockPrices) {
        StockPrice stockPrice = stockPrices.get(0);
        int year = Integer.parseInt(stockPrice.getDate().substring(0, 4));

        return year == Finals.END_YEAR;
    }

    public static boolean checkIfItsEndMonthAndEndYear(List<StockPrice> stockPrices, String month) {
        int monthInt = Integer.parseInt(month);
        boolean isItEndMonth = monthInt == Finals.END_MONTH;
        boolean isItEndYear = checkIfItsEndYear(stockPrices);

        return isItEndMonth && isItEndYear;
    }

    public static int getDayFromStockPrice(StockPrice stockPrice) {
        String date = stockPrice.getDate();
        int firstHyphen = date.indexOf("-");
        int secondHyphen = date.indexOf("-", firstHyphen + 2);
        String day = date.substring(date.length() - 2, date.length());

        return Integer.parseInt(day);
    }

    public static double calcPercent(double smaller, double bigger) {
        return ((bigger - smaller) / smaller) * 100;
    }

    public static int getYearFromStockPrice(StockPrice stockPrice) {
        String date = stockPrice.getDate();

        return Integer.parseInt(date.substring(0, 4));
    }

    public static int getMonthFromStockPrice(StockPrice stockPrice) {
        String date = stockPrice.getDate();
        int firstHyphen = date.indexOf("-");

        return Integer.parseInt(date.substring((firstHyphen + 1), (firstHyphen + 3)));
    }
}
