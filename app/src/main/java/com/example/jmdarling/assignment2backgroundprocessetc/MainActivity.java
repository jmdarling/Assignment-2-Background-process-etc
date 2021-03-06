/**
 * Created by:
 *  Jonathan Darling - jxd128130
 *  Stephen Kuehl - skuehl
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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The primary activity for the application.
 * This provides an interface for the user to search for a stock symbol and displays the relevant
 * data.
 *
 * Created by Jonathan Darling and Stephen Kuehl.
 */
public class MainActivity extends Activity {
    /*Debug tag for downloadUrl class that will output a response code
    and returns the connection's status code. This is a useful way of getting
    additional information about the connection. A status code of 200
    indicates success.*/
    private static final String DEBUG_TAG = "HttpExample";

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

        // Gets the URL from the UI's text field.
        String startUrl = "http://utdallas.edu/~jxc064000/2015Spring/";
        String enteredUrl = mEditQuery.getText().toString().toUpperCase();
        String stringUrl = startUrl + enteredUrl + ".txt";

        // Ensure that we have an active network connection.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadStockSymbolInfo().execute(stringUrl);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "You do not have an active web connection.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The purpose of this method is to download stock symbol information
     * Once this method is called the order of the process is as follows
     * doInBackground: Downloads the webpage content and checks for exceptions
     * onPostExecute: Additional functionality
     *
     * Created by Stephen Kuehl
     */
    public class DownloadStockSymbolInfo extends AsyncTask<String, URL, String> {

        /**
         * When the user selects enter, this method will do a background check
         * for new website stock symbol information
         *
         * @parm String... urls
         *  Run the downloadUrl in the background
         *
         * Created by Stephen Kuehl
         */
        @Override
        protected String doInBackground(String... urls) {

            try {
                return downloadUrl(urls[0]);
            } catch (SocketTimeoutException e) {
                return "timeout";
            } catch (IOException e) {
                return "404";
            }
        }

        /**
         * /*Once the text file has completed downloading, this method will post it
         * into the stock adapter
         *
         * @param result
         *  Checks the result variable for response errors such as 404
         *
         * Created by Stephen Kuehl and Jonathan Darling
         */
        protected void onPostExecute(String result) {

            // Push data into the array class if successful
            // If not parsed successfully push the exception to the screen
            if (!((result.compareTo("404") == 0) || (result.compareTo("timeout") == 0))) {
                try {
                    ArrayList<Stock> parsedStocks = parseFileToStockArrayList(result);
                    mStocks.clear();
                    mStocks.addAll(parsedStocks);
                    mStocksAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    System.err.println("The file to parse was incorrectly formatted.");
                    e.printStackTrace();
                }
            } else if (result.compareTo("404") == 0) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Invalid Symbol")
                        .setMessage("You have entered an invalid stock symbol.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            /**
                             * Button listener for the dialog.
                             * Unused.
                             *
                             * @param dialogInterface
                             *  The dialog being clicked.
                             * @param clicked
                             *  The button in the dialog being clicked.
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int clicked) {
                            }
                        })
                        .create().show();
            } else if (result.compareTo("timeout") == 0) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Timeout")
                        .setMessage("Your request has timed out. Please enter another symbol.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            /**
                             * Button listener for the dialog.
                             * Unused.
                             *
                             * @param dialogInterface
                             *  The dialog being clicked.
                             * @param clicked
                             *  The button in the dialog being clicked.
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int clicked) {
                            }
                        })
                        .create().show();
            }
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Given a URL, establishes an HttpUrlConnection and retrieves the  web page content
     * as a InputStream, which it returns as a string using the readIt method.
     *
     * @param thisUrl
     *  The stock symbol entered by the user appended to the the core website URL
     *
     * @return
     *  Returns the web content as a string
     *
     * @throws IOException
     *  If a connection cannot be made, close the connection
     *
     * Created by Stephen Kuehl
     */
    private String downloadUrl(String thisUrl) throws IOException {
        InputStream is = null;

        // attempt a connection to the URL and download the webpage
        try {
            URL url = new URL(thisUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(45000 /* milliseconds */);
            conn.setConnectTimeout(45000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    /**
     * The webpage data is pushed into the readIt function with the stream variable
     *
     * @param stream
     *  The webpage information
     *
     * @return
     *  The webpage data into strings
     *
     * @throws IOException
     *  Throws an exception if the information is not available
     *
     * @throws UnsupportedEncodingException
     *  Throws an exception if the information cannot be captured due to format restrictions
     *
     * Created by Stephen Kuehl and Jonathan Darling
     */
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line).append("\n");
        }
        return stringBuffer.toString();
    }

    /**
     * Parses a stock data file into an ArrayList of Stock objects.
     *
     * @param fileContents
     *  The stock data file's contents.
     *
     * @return
     *  An ArrayList of Stock objects parsed from the stock data file.
     *
     * @throws Exception
     *  Throws an exception if the formatting of the file is incorrect.
     *
     * Created by Jonathan Darling.
     */
    private ArrayList<Stock> parseFileToStockArrayList(String fileContents) throws Exception {
        ArrayList<Stock> stocks = new ArrayList<>();
        String[] fileContentsSplit;

        // Split fileContents by line. Each line represents a Stock.
        fileContentsSplit = fileContents.split("\n");

        // For each line in the file, create a new Stock with the contents. Start iterating at 1
        // because the first item in the array is the header line, which we don't need.
        for (int i = 1; i < fileContentsSplit.length; i++) {

            // Stock values are stored as CSVs.
            String[] lineContentsSplit = fileContentsSplit[i].split(",");

            // Ensure that we have the correct number of items per line, if not, the file is
            // formatted incorrectly.
            if (lineContentsSplit.length == 7) {
                String date = lineContentsSplit[0];
                String open = lineContentsSplit[1];
                String high = lineContentsSplit[2];
                String low = lineContentsSplit[3];
                String close = lineContentsSplit[4];
                String volume = lineContentsSplit[5];
                String adjClose = lineContentsSplit[6];
                stocks.add(new Stock(date, open, high, low, close, volume, adjClose));
            } else {
                throw new Exception("Invalid file format.");
            }

        }
        return stocks;
    }

}
