package com.myniotech.moneycontrol.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.presentation.ISpentListPresenter;
import com.myniotech.moneycontrol.view.adapter.SpentListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentListFragment extends Fragment implements SpentListFragmentView, SpentListAdapter.SpentAdapterListener {


    @Inject
    ISpentListPresenter spentListPresenter;


    @BindView(R.id.rvSpentList)

    RecyclerView rvSpentList;

    protected SpentListAdapter spentListAdapter;


    Unbinder unbinder;


    public static SpentListFragment getInstance() {
        return new SpentListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MoneyControlApp) getActivity().getApplication()).getApplicationComponent().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.spent_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        spentListPresenter.setView(this);

    }

    @Override
    public void setSpentList(List<Spent> spentList) {

        spentListAdapter = new SpentListAdapter(spentList, this);
        rvSpentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSpentList.setAdapter(spentListAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDeleteSpent(Spent spent) {
        spentListPresenter.deleteSpent(spent);


    }
}
