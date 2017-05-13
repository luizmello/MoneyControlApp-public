package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.view.fragment.SpentListFragmentView;
import com.myniotech.moneycontrol.view.fragment.SpentOnLineListFragment;
import com.myniotech.moneycontrol.view.fragment.SpentOnLineListFragmentView;

/**
 * Created by luiz on 20/04/17.
 */

public interface ISpentOnLineListPresenter {

    void setView(SpentOnLineListFragmentView view);

    void loadSpentList();

    void deleteSpent(Spent spent);

}
