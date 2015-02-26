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
 * This module contains the StockAdapter class, an extension of the RelativeLayout.
 */

package com.example.jmdarling.assignment2backgroundprocessetc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Layout to represent a Stock object.
 *
 * Created by Jonathan Darling.
 */
public class StockLayout extends RelativeLayout {
    private TextView mSymbolTextView;
    private TextView mDateTextView;
    private TextView mOpenTextView;
    private TextView mHighTextView;
    private TextView mLowTextView;
    private TextView mCloseTextView;
    private TextView mVolumeTextView;
    private TextView mAdjustedCloseTextView;

    /**
     * Constructor.
     *
     * @param context
     *  The Context the view is running in.
     * @param attributeSet
     *  The attributes of the XML tag that is inflating the view.
     *
     * Created by Jonathan Darling.
     */
    public StockLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * Final step of View inflation.
     * Attach handles to the TextViews in the inflated View.
     *
     * Created by Jonathan Darling.
     */
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

    /**
     * Set the Stock to be used for the View.
     * This Stock's data will be used to populate the View's TextViews.
     *
     * @param stock
     *  The Stock object whose data will be used to populate the View's TextViews.
     */
    public void setStock(Stock stock) {
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
