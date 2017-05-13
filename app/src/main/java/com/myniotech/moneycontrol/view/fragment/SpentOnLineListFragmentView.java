package com.myniotech.moneycontrol.view.fragment;

import com.myniotech.moneycontrol.model.spent.Spent;

import java.util.List;

/**
 * Created by luiz on 20/04/17.
 */

public interface SpentOnLineListFragmentView {

    void setSpentList(List<Spent> spentList);
    void showDeleteStatus(String status);

}
