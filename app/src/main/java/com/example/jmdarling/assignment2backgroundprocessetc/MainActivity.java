/**
 * Created by:
 *  Jonathan Darling - jxd128130
 *  Stephen Kuehl -
 *
 * Development started:
 *  24 February 2015.
 *
 * Written for:
 *  Assignment 2: Background process, etc. - CS4V95.015 Undergraduate Topics in Computer Science
 *
 * This module contains the main (and only) activity in this application.
 */

package com.example.jmdarling.assignment2backgroundprocessetc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * The primary activity for the application.
 * This provides an interface for the user to search for a stock symbol and displays the relevant
 * data.
 *
 * Created by Jonathan Darling and Stephen Kuehl.
 */
public class MainActivity extends Activity {
    // ListView data and adapter.
    ArrayList<Stock> mStocks;
    StockAdapter mStocksAdapter;

    // Handles for layout elements.
    ProgressBar mProgressBar;
    EditText mEditQuery;
    ListView mListView;

    /**
     * Called when the activity is starting.
     * This gives handles to layout elements, and sets up the ListView.
     *
     * @param savedInstanceState
     *  If the activity is being re-initialized after previously being shut down then this Bundle
     *  contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *
     * Created by Jonathan Darling and Stephen Kuehl.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the ProgressBar.
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        // Setup our query control.
        mEditQuery = (EditText)findViewById(R.id.edit_query);

        // Setup the adapter for the ListView.
        mListView = (ListView)findViewById(R.id.list_view);
        mStocks = new ArrayList<>();
        mStocksAdapter = new StockAdapter(this, mStocks);
        mListView.setAdapter(mStocksAdapter);
    }

    /**
     * Click listener for the submit_query button.
     * Gets the user entered stock symbol and triggers an AsyncTask to download the appropriate data
     * from the web and display it.
     *
     * @param view
     *  The view the listener is applied to.
     *
     * Created by Jonathan Darling.
     */
    public void submitQueryButtonListener(View view) {
        String stockSymbol;

        // Get the stock symbol that the user wishes to search for.
        stockSymbol = mEditQuery.getText().toString();

        // Get the data from the web for the user specified stock symbol and update the ListView.
        new RunAsyncTask().execute(stockSymbol);
    }
}
