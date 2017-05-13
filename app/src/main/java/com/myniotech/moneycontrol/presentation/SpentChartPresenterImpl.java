package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.view.fragment.SpentChartFragmentView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luiz on 24/04/17.
 */

public class SpentChartPresenterImpl implements ISpentChartPresenter {


    private SpentChartFragmentView chartFragmentView;
    private ISpentRepository spentRepository;

    public SpentChartPresenterImpl(ISpentRepository spentRepository) {

        this.spentRepository = spentRepository;
    }

    @Override
    public void setView(SpentChartFragmentView chartFragmentView) {

        this.chartFragmentView = chartFragmentView;

        loadPieChart();
        loadBarChart();
        loadLineChart();
    }

    @Override
    public void loadPieChart() {

        chartFragmentView.loadPieChartWithData(getTotalCC(), getTotalCash());

    }

    @Override
    public void loadBarChart() {
        chartFragmentView.loadBarChartWithData(getTotalCC(), getTotalCash());
    }

    @Override
    public void loadLineChart() {
        chartFragmentView.loadLineChartWithData(spentRepository.getAll());
    }

    @Override
    public float getTotalCash() {

        List<Spent> spentList = spentRepository.getAllCash();

        BigDecimal totalSpent = new BigDecimal("0");

        for (Spent spent :
                spentList) {
            totalSpent = totalSpent.add(spent.getValue());
        }

        return totalSpent.floatValue();
    }

    @Override
    public float getTotalCC() {

        List<Spent> spentList = spentRepository.getAllCC();

        BigDecimal totalSpent = new BigDecimal("0");

        for (Spent spent :
                spentList) {
            totalSpent = totalSpent.add(spent.getValue());
        }

        return totalSpent.floatValue();
    }


}
