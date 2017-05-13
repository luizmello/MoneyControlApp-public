package com.myniotech.moneycontrol.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.MoneyChartFormatter;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.presentation.ISpentChartPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentChartFragment extends Fragment implements SpentChartFragmentView {


    @BindView(R.id.spentChart)
    PieChart spentChart;

    @BindView(R.id.spentBarChart)
    BarChart spentBarChart;

    @Inject
    ISpentChartPresenter spentChartPresenter;


    public static SpentChartFragment getInstance() {
        return new SpentChartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MoneyControlApp) getActivity().getApplication()).getApplicationComponent().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.spent_chart_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        spentChartPresenter.setView(this);

    }

    @Override
    public void loadPieChartWithData(float cc, float cash) {

        spentChart.setUsePercentValues(true);

        ArrayList<PieEntry> yvalues = new ArrayList<>();

        yvalues.add(new PieEntry(cash, "Cash"));
        yvalues.add(new PieEntry(cc, "Credit Card"));

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        dataSet.setValues(yvalues);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        spentChart.setData(data);
        spentChart.setDrawEntryLabels(true);
        spentChart.setDescription(null);

    }

    @Override
    public void loadBarChartWithData(float cc, float cash) {


        ArrayList<BarEntry> yvalues = new ArrayList<>();

        yvalues.add(new BarEntry(0, cash, "Cash"));
        yvalues.add(new BarEntry(1, cc, "Credit Card"));

        BarDataSet dataSet = new BarDataSet(yvalues, "");

        dataSet.setValues(yvalues);
        dataSet.setColors(Color.BLUE, Color.GREEN);

        BarData data = new BarData(dataSet);

        data.setValueFormatter(new MoneyChartFormatter());
        spentBarChart.setData(data);
        spentBarChart.setBackgroundColor(Color.WHITE);
        spentBarChart.setDescription(null);

        XAxis x = spentBarChart.getXAxis();
        x.setDrawGridLines(false);
        x.setDrawLabels(false);

        YAxis yLeft = spentBarChart.getAxisLeft();
        yLeft.setAxisMinimum(0f);
        yLeft.setDrawGridLines(false);
        yLeft.setDrawLabels(false);
        yLeft.setDrawZeroLine(false);

        YAxis yRight = spentBarChart.getAxisRight();
        yRight.setAxisMinimum(0f);
        yRight.setDrawLabels(false);
        yRight.setDrawZeroLine(true);
        yRight.setZeroLineColor(Color.BLACK);
        yRight.setZeroLineWidth(2f);

        Legend legend = spentBarChart.getLegend();
        legend.setEnabled(true);


        List<LegendEntry> entries = new ArrayList<>();

        LegendEntry cashEntry = new LegendEntry();
        cashEntry.label = "Cash";
        cashEntry.form = Legend.LegendForm.SQUARE;
        cashEntry.formColor = Color.BLUE;
        entries.add(cashEntry);


        LegendEntry creditEntry = new LegendEntry();
        creditEntry.label = "Credit Card";
        creditEntry.form = Legend.LegendForm.SQUARE;
        creditEntry.formColor = Color.GREEN;
        entries.add(creditEntry);

        legend.setCustom(entries);

    }

    @Override
    public void loadLineChartWithData(List<Spent> spents) {
        // TODO: 25/04/17 make graphic with spent by day
    }


}
