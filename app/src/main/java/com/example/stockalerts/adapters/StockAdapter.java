package com.example.stockalerts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockalerts.R;
import com.example.stockalerts.models.Ticker;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {
    private ArrayList<Ticker> tickerList;

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        private final TextView stockRowTicker;
        private final TextView stockRowPrice;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            this.stockRowTicker = itemView.findViewById(R.id.stock_row_ticker);
            this.stockRowPrice = itemView.findViewById(R.id.stock_row_price);
        }

        public void setTickerInfo(Ticker ticker){
            this.stockRowTicker.setText(ticker.name);
            this.stockRowPrice.setText(String.valueOf(ticker.price));
        }
    }
    public StockAdapter(){}
    public void setTickerList(ArrayList<Ticker> tickerList){
        this.tickerList = tickerList;
    }
    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_row_item, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        holder.setTickerInfo(tickerList.get(position));
    }

    @Override
    public int getItemCount() {
        return tickerList.size();
    }
}
