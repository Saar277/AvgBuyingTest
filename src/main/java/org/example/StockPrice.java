package org.example;

public class StockPrice {
    private String date;
    private double price;

    public StockPrice(String date, double price) {
        this.setDate(date);
        this.setPrice(price);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static StockPrice createStockPrice(String[] metadata) {
        String date = metadata[0];
        double price = Double.parseDouble(metadata[4]);

        return new StockPrice(date, price);
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
