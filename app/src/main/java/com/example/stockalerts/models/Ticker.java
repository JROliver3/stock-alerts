package com.example.stockalerts.models;

import com.example.stockalerts.enums.StockMarket;

public class Ticker {
    public String ticker;
    public String name;
    public String market;
    public String locale;
    public String currency;
    public Boolean active;
    public String primaryExch;
    public String type;
    public String updated;
    public Double price;

    public Ticker(){}

    public Ticker(String ticker, String name, String market, String locale, String currency,
                  Boolean active, String primaryExch, String type, String updated, Double price) {
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.locale = locale;
        this.currency = currency;
        this.active = active;
        this.primaryExch = primaryExch;
        this.type = type;
        this.updated = updated;
        this.price = price;
    }
}
