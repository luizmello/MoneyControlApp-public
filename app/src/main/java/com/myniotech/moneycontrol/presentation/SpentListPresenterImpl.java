package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.view.fragment.SpentListFragmentView;

import java.util.List;

/**
 * Created by luiz on 22/04/17.
 */

public class SpentListPresenterImpl implements ISpentListPresenter {


    private ISpentRepository spentRepository;
    List<Spent> spentList;
    private SpentListFragmentView spentListFragmentView;

    public SpentListPresenterImpl(ISpentRepository spentRepository) {

        this.spentRepository = spentRepository;
    }

    @Override
    public void setView(SpentListFragmentView view) {
        this.spentListFragmentView = view;

        loadSpentList();

    }

    @Override
    public void loadSpentList() {

        spentList = spentRepository.getAll();
        spentListFragmentView.setSpentList(spentList);
    }

    @Override
    public void deleteSpent(Spent spent) {
        spentRepository.delete(spent);
        loadSpentList();

    }
}
