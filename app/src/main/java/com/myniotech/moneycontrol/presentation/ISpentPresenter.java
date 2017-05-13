package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.view.fragment.NewSpentFragmentView;

/**
 * Created by luiz on 20/04/17.
 */

public interface ISpentPresenter {

    void setView(NewSpentFragmentView view);

    void saveLocalSpent();

    void saveCloudSpent();

    void loadTotalSpent();

    void loadTotalSpentWithCC();

    void loadTotalSpentWithCash();

    void updateView();

}
