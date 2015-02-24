package com.example.jmdarling.assignment2backgroundprocessetc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by jmdarling on 2/24/15.
 */
public class StockAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Stock> mStocks;

    public StockAdapter(Context context, ArrayList<Stock> stocks) {
        super();
        mContext = context;
        mStocks = stocks;
    }

    @Override
    public int getCount() {
        return mStocks.size();
    }

    @Override
    public Stock getItem(int index) {
        return mStocks.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StockLayout stockLayout;

        if (convertView == null) {
            stockLayout = (StockLayout) View.inflate(mContext, R.layout.table_row_listview_layout, null);
        } else {
            stockLayout = (StockLayout) convertView;
        }
        stockLayout.setStock(mStocks.get(position));

        return stockLayout;
    }
}
