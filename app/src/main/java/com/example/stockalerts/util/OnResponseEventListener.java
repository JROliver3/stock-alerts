package com.example.stockalerts.util;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface OnResponseEventListener {
    void onResponseEvent(JSONObject response) throws JSONException;
    void onErrorResponseEvent(VolleyError error);
}
