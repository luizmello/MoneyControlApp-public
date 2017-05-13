package com.myniotech.moneycontrol.app;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by luiz on 24/04/17.
 */

public class MoneyChartFormatter implements IValueFormatter {


    public MoneyChartFormatter() {

    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return Utils.longToCurrency((long)value);
    }
}
