package com.example.stockalerts;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockalerts.models.Ticker;
import com.example.stockalerts.services.polygon.PolygonApi;
import com.example.stockalerts.services.polygon.PolygonRequestOptions;
import com.example.stockalerts.util.Builder;
import com.example.stockalerts.util.ObjectBuilder;
import com.example.stockalerts.util.ObjectSerializer;
import com.example.stockalerts.util.OnResponseEventListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
* TODO:
*  1. Change name of CreateNewRequest and move it to the main thread to execute upon app init //
*  2. Using the ticker list, populate an display with ticker information //
*  3. Call the Last Trade for a Symbol v2 API route to get trade numbers for each rendered symbol //
*  4. Before actually rendering the symbols, get each trade and make sure each symbol renders
*  5. Enable pagination for the symbols to get more results. Call the api again once the page
*     runs out.
*  6. After pagination is enabled, work on filtering out symbols using search and filter.
*  7. Enable clicking on a symbol to get more details.
*  8. On the symbol page add an alert button.
*  9. Create an alert configuration menu to assign an alert to a specified min and max value
*  10. Enable the app to alert the phone when a specific value is reached by a ticker
*  11. Enable a websocket in order to get real-time symbol data and display it live on the main
*      screen.
*  12. Enhance the UI and brainstorm other possible enhancements.
* */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createNewRequest();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}