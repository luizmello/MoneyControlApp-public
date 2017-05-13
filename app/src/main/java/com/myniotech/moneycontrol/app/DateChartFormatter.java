package com.myniotech.moneycontrol.app;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by luiz on 24/04/17.
 */

public class DateChartFormatter implements IAxisValueFormatter {


    public DateChartFormatter() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return Utils.dateFormat(value);
    }
}
