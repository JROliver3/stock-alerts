package com.example.stockalerts.services.polygon;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockalerts.R;
import com.example.stockalerts.enums.StockMarket;
import com.example.stockalerts.models.Ticker;
import com.example.stockalerts.util.ObjectBuilder;
import com.example.stockalerts.util.ObjectSerializer;
import com.example.stockalerts.util.OnResponseEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PolygonApi {
    private final String ENDPOINT = "https://api.polygon.io/";
    private String version = "v2";
    private String ref = "reference";
    private String apiKey = "BkF5SM8jckTNKDiPrMtNZeHBhsd1Z3jC";
    private ArrayList<StringRequest> requests = new ArrayList<StringRequest>();
    private RequestQueue queue;
    private PolygonRequestOptions options;

    public PolygonApi(Context context){
        this.queue = Volley.newRequestQueue(context);
        this.options = new PolygonRequestOptions().GetDefaultOptions();
    }
    public PolygonApi(Context context, PolygonRequestOptions options){
        this.queue = Volley.newRequestQueue(context);
        this.options = options;
    }
    public ArrayList<Ticker> GetTickerList(){
        final ArrayList<Ticker> tickerList = new ArrayList<>();
        get("tickers", new OnResponseEventListener(){
            @Override
            @SuppressWarnings("unchecked")
            public void onResponseEvent(JSONObject response) throws JSONException {
                try {
                    ObjectSerializer<Ticker> serializer = new ObjectSerializer<Ticker>();
                    JSONArray tickerJSONArray = response.getJSONArray("tickers");
                    for(int i = 0; i < tickerJSONArray.length(); i++){
                        Ticker ticker = serializer
                                .DeserializeJSONObject(tickerJSONArray.getJSONObject(i),
                                        new ObjectBuilder());
                        tickerList.add(ticker);
                    }
                    Log.d("ticker name is: ", tickerList.get(0).name);
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
        return tickerList;
    }
    public void get(String path, final OnResponseEventListener listener){
        String url = ApiQueryBuilder(path);
        Log.d("api url: ", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    listener.onResponseEvent(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponseEvent(error);
            }
        });
        this.queue.add(jsonObjectRequest);
    }
    public void setApiVersion(String version){
        this.version = version;
    }

    public String ApiQueryBuilder(String path){
        if(path.equals("tickers")){
            return this.ENDPOINT.concat(this.version + "/" + this.ref + "/" + path + "?sort="
                    + this.options.sort + "&type=" + this.options.type + "&market="
                    + this.options.market + "&locale=" + this.options.locale + "&search="
                    + this.options.filter + "&perpage=" + this.options.pages + "&page=1"
                    + "&active=" + this.options.active + "&apiKey=" + this.apiKey);
        } else {
            return this.ENDPOINT.concat((this.version + path +  "?&apiKey=" + this.apiKey));
        }
    }
    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }
}
