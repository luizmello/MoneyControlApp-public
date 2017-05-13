package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.view.fragment.SpentChartFragmentView;

/**
 * Created by luiz on 24/04/17.
 */

public interface ISpentChartPresenter {

    void setView(SpentChartFragmentView chartFragmentView);
    void loadPieChart();
    void loadBarChart();
    void loadLineChart();
    float getTotalCash();
    float getTotalCC();
}
