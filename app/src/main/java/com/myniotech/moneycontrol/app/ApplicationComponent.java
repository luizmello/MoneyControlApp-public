package com.myniotech.moneycontrol.app;

import com.myniotech.moneycontrol.services.SyncService;
import com.myniotech.moneycontrol.services.firebase.FirebaseMsgService;
import com.myniotech.moneycontrol.services.firebase.MoneyControlInstanceIDService;
import com.myniotech.moneycontrol.view.activity.MainActivity;
import com.myniotech.moneycontrol.view.adapter.SpentListAdapter;
import com.myniotech.moneycontrol.view.fragment.NewSpentFragment;
import com.myniotech.moneycontrol.view.fragment.SpentChartFragment;
import com.myniotech.moneycontrol.view.fragment.SpentListFragment;
import com.myniotech.moneycontrol.view.fragment.SpentOnLineListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luiz on 19/04/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(NewSpentFragment newSpentFragment);

    void inject(SpentListFragment spentListFragment);

    void inject(SpentChartFragment spentChartFragment);

    void inject(SpentOnLineListFragment spentOnLineListFragment);

    void inject(MoneyControlInstanceIDService moneyControlInstanceIDService);

    void inject(FirebaseMsgService firebaseMsgService);

    void inject(SyncService syncService);

}
