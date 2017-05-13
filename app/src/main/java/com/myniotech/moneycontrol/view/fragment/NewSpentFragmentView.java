package com.myniotech.moneycontrol.view.fragment;

import com.myniotech.moneycontrol.model.spent.SpentType;

/**
 * Created by luiz on 20/04/17.
 */

public interface NewSpentFragmentView {

    String getSpentValue();
    SpentType getSpentType();

    void setTotalSpent(String totalSpent);
    void setTotalSpentWithCC(String totalSpentWithCC);
    void setTotalSpentWithCash(String totalSpentWithCash);

    void showSpentSavedLocalMessage();
    void showErrorOnSaveLocalSpent();
    void showSpentSavedCloudMessage();
    void showErrorOnSaveCloudSpent();

}
