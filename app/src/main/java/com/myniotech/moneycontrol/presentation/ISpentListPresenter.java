package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.view.fragment.SpentListFragmentView;

/**
 * Created by luiz on 20/04/17.
 */

public interface ISpentListPresenter {

    void setView(SpentListFragmentView view);
    void loadSpentList();
    void deleteSpent(Spent spent);

}
