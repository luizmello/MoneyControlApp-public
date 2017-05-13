package com.myniotech.moneycontrol.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.app.RxBus;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.presentation.ISpentListPresenter;
import com.myniotech.moneycontrol.presentation.ISpentOnLineListPresenter;
import com.myniotech.moneycontrol.view.adapter.SpentListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentOnLineListFragment extends Fragment implements SpentOnLineListFragmentView, SpentListAdapter.SpentAdapterListener {


    @Inject
    ISpentOnLineListPresenter spentOnLineListPresenter;

    @BindView(R.id.rvSpentOnLineList)
    RecyclerView rvSpentList;

    protected SpentListAdapter spentListAdapter;

    @Inject
    RxBus rxBus;

    private final CompositeDisposable disposables = new CompositeDisposable();


    public static SpentOnLineListFragment getInstance() {
        return new SpentOnLineListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MoneyControlApp) getActivity().getApplication()).getApplicationComponent().inject(this);

        spentOnLineListPresenter.setView(this);

        disposables.add(rxBus
                .toObserverable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Events.FinishSyncEvent) {
                            //Toast.makeText(getContext(), "Finish Sync Event", Toast.LENGTH_SHORT).show();
                            spentOnLineListPresenter.loadSpentList();
                        }
                    }
                }));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.spent_on_line_list_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spentOnLineListPresenter.loadSpentList();

    }

    @Override
    public void setSpentList(List<Spent> spentList) {

        rvSpentList.setLayoutManager(new LinearLayoutManager(getActivity()));

        spentListAdapter = new SpentListAdapter(spentList, this);

        rvSpentList.setAdapter(spentListAdapter);

    }

    @Override
    public void showDeleteStatus(String status) {
        Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSpent(Spent spent) {
        spentOnLineListPresenter.deleteSpent(spent);

    }
}
