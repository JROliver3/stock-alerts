package com.example.stockalerts.services.polygon;

import com.example.stockalerts.enums.StockMarket;

public class PolygonRequestOptions {
    protected String path;
    protected String sort;
    protected String type;
    protected Enum<StockMarket> market;
    protected String locale;
    protected String filter;
    protected int pages;
    protected boolean active;

    public PolygonRequestOptions(){}

    public PolygonRequestOptions GetDefaultOptions(){
        this.sort = "ticker";
        this.type = "";
        this.market = StockMarket.STOCKS;
        this.locale = "us";
        this.filter = "";
        this.pages = 10;
        this.active = true;
        return this;
    }

    public PolygonRequestOptions(String sort, String type, Enum<StockMarket> market, String locale,
                                 String filter, int pages, boolean active){
        this.sort = sort;
        this.type = type;
        this.market = market;
        this.locale = locale;
        this.filter = filter;
        this.pages = pages;
        this.active = active;
    }
}
