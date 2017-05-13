package com.myniotech.moneycontrol.view.fragment;

import com.myniotech.moneycontrol.model.spent.Spent;

import java.util.List;

/**
 * Created by luiz on 24/04/17.
 */

public interface SpentChartFragmentView {

    void loadPieChartWithData(float cc, float cash);
    void loadBarChartWithData(float cc, float cash);
    void loadLineChartWithData(List<Spent> spents);

}
