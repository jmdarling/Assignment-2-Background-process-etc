package com.example.jmdarling.assignment2backgroundprocessetc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jmdarling on 2/24/15.
 */
public class StockLayout extends LinearLayout {
    private Stock mStock;
    private TextView mSymbolTextView;
    private TextView mDateTextView;
    private TextView mOpenTextView;
    private TextView mHighTextView;
    private TextView mLowTextView;
    private TextView mCloseTextView;
    private TextView mVolumeTextView;
    private TextView mAdjustedCloseTextView;

    public StockLayout(Context context) {
        super(context);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        mSymbolTextView = (TextView)findViewById(R.id.symbol);
        mDateTextView = (TextView)findViewById(R.id.date);
        mOpenTextView = (TextView)findViewById(R.id.open);
        mHighTextView = (TextView)findViewById(R.id.high);
        mLowTextView = (TextView)findViewById(R.id.low);
        mCloseTextView = (TextView)findViewById(R.id.close);
        mVolumeTextView = (TextView)findViewById(R.id.volume);
        mAdjustedCloseTextView = (TextView)findViewById(R.id.adjusted_close);
    }

    public void setStock(Stock stock) {
        mStock = stock;
        mSymbolTextView.setText(stock.getSymbol());
        mDateTextView.setText(stock.getDate());
        mOpenTextView.setText(stock.getOpen());
        mHighTextView.setText(stock.getHigh());
        mLowTextView.setText(stock.getLow());
        mCloseTextView.setText(stock.getClose());
        mVolumeTextView.setText(stock.getVolume());
        mAdjustedCloseTextView.setText(stock.getAdjustedClose());
    }
}
