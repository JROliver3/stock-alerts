package com.example.stockalerts.util;

import com.example.stockalerts.models.Ticker;

public class ObjectBuilder implements Builder {
    @Override
    public Ticker build() {
        return new Ticker();
    }
}
