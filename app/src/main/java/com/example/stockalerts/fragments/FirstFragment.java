package com.example.stockalerts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.stockalerts.R;
import com.example.stockalerts.adapters.StockAdapter;
import com.example.stockalerts.models.Ticker;
import com.example.stockalerts.services.polygon.PolygonApi;
import com.example.stockalerts.util.ObjectBuilder;
import com.example.stockalerts.util.ObjectSerializer;
import com.example.stockalerts.util.OnResponseEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/* TODO: Need to migrate to either paid Polygon API key in order to get price info or utilize ML
   in order to generate stock predictions.
* */
public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        PolygonApi api = new PolygonApi(context);
        final ArrayList<Ticker> tickerList = new ArrayList<>();
        api.get("tickers", new OnResponseEventListener(){
            @Override
            @SuppressWarnings("unchecked")
            public void onResponseEvent(JSONObject response) throws JSONException {
                try {
                    ObjectSerializer<Ticker> serializer = new ObjectSerializer<Ticker>();
                    JSONArray tickerJSONArray = response.getJSONArray("tickers");
                    for(int i = 0; i < tickerJSONArray.length(); i++){
                        final Ticker ticker = serializer
                                .DeserializeJSONObject(tickerJSONArray.getJSONObject(i),
                                        new ObjectBuilder());
                    }
                    populateStockViewList(tickerList);
                } catch(Exception e){
                    Log.e("ticker", "serialization failed", e.getCause());
                    e.printStackTrace();
                }
            }
            @Override
            public void onErrorResponseEvent(VolleyError error) {
                Log.e("ticker", "api call failed", error.getCause());
                error.printStackTrace();
            }
        });
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    public void populateStockViewList(ArrayList<Ticker> tickerList){
        RecyclerView stockView = getView().findViewById(R.id.stock_view_list);
        stockView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        StockAdapter stockAdapter = new StockAdapter();
        stockAdapter.setTickerList(tickerList);
        stockView.setAdapter(stockAdapter);
    }

}