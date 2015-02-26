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
 * This module contains the Stock class.
 */

package com.example.jmdarling.assignment2backgroundprocessetc;

/**
 * Represents a stock.
 *
 * Created by Jonathan Darling.
 */
public class Stock {
    private String mSymbol;
    private String mDate;
    private String mOpen;
    private String mHigh;
    private String mLow;
    private String mClose;
    private String mVolume;
    private String mAdjustedClose;

    /**
     * Constructor.
     *
     * @param symbol
     *  The stock's symbol.
     * @param date
     *  The stock's date.
     * @param open
     *  The stock's opening price.
     * @param high
     *  The stock's high price.
     * @param low
     *  The stock's low price.
     * @param close
     *  The stock's closing price.
     * @param volume
     *  The stock's volume.
     * @param adjustedClose
     *  The stock's adjusted closing price.
     *
     * Created by Jonathan Darling.
     */
    public Stock(String symbol, String date, String open, String high, String low, String close, String volume, String adjustedClose) {
        mSymbol = symbol;
        mDate = date;
        mOpen = open;
        mHigh = high;
        mLow = low;
        mClose = close;
        mVolume = volume;
        mAdjustedClose = adjustedClose;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String mSymbol) {
        this.mSymbol = mSymbol;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getOpen() {
        return mOpen;
    }

    public void setOpen(String mOpen) {
        this.mOpen = mOpen;
    }

    public String getHigh() {
        return mHigh;
    }

    public void setHigh(String mHigh) {
        this.mHigh = mHigh;
    }

    public String getLow() {
        return mLow;
    }

    public void setLow(String mLow) {
        this.mLow = mLow;
    }

    public String getClose() {
        return mClose;
    }

    public void setClose(String mClose) {
        this.mClose = mClose;
    }

    public String getVolume() {
        return mVolume;
    }

    public void setVolume(String mVolume) {
        this.mVolume = mVolume;
    }

    public String getAdjustedClose() {
        return mAdjustedClose;
    }

    public void setAdjustedClose(String mAdjustedClose) {
        this.mAdjustedClose = mAdjustedClose;
    }
}
