/**
 * Created by:
 *  Jonathan Darling - jxd128130
 *
 * Development started:
 *  24 February 2015.
 *
 * Written for:
 *  Assignment 2: Background process, etc. - CS4V95.015 Undergraduate Topics in Computer Science
 *
 * This module contains the StockAdapter class, an extension of the BaseAdapter.
 */

package com.example.jmdarling.assignment2backgroundprocessetc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Adapter for Stock objects.
 *
 * Created by Jonathan Darling.
 */
public class StockAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Stock> mStocks;

    /**
     * Constructor.
     *
     * @param context
     *  Activity Context to be used in layout inflation.
     * @param stocks
     *  ArrayList of Stock objects to populate the adapter with.
     *
     *  Created by Jonathan Darling.
     */
    public StockAdapter(Context context, ArrayList<Stock> stocks) {
        super();
        mContext = context;
        mStocks = stocks;
    }

    /**
     * Returns the number of items in the adapter.
     *
     * @return
     *  The number of items in the adapter.
     *
     * Created by Jonathan Darling.
     */
    @Override
    public int getCount() {
        return mStocks.size();
    }

    /**
     * Returns the Stock object at the specified index.
     *
     * @param index
     *  The index to retrieve the Stock object from.
     *
     * @return
     *  The Stock object at the specified index.
     *
     * Created by Jonathan Darling.
     */
    @Override
    public Stock getItem(int index) {
        return mStocks.get(index);
    }

    /**
     * Returns the item ID for the item at the specified index.
     * (This was only overridden out of necessity and is not used)
     *
     * @param index
     *  The index to retrieve the item ID for.
     *
     * @return
     *  The item ID for the item at the specified index.
     *
     * Created by Jonathan Darling.
     */
    @Override
    public long getItemId(int index) {
        return index;
    }

    /**
     * Returns a View representing the Stock object at the specified index.
     *
     * @param index
     *  The index to create the View for.
     * @param convertView
     *  The View to reuse.
     * @param parent
     *  The parent View this View will be attached to.
     *
     * @return
     *  The view representing the Stock object at the specified index.
     *
     * Created by Jonathan Darling.
     */
    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        StockLayout stockLayout;

        // Only reuse the convertView if it is not null.
        if (convertView == null) {
            stockLayout = (StockLayout) View.inflate(mContext, R.layout.table_row_listview_layout, null);
        } else {
            stockLayout = (StockLayout) convertView;
        }

        // Set the Stock for the view. This will populate it with the data contained in the Stock.
        stockLayout.setStock(mStocks.get(index));

        return stockLayout;
    }
}
