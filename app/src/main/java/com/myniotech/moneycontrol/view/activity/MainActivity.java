package com.myniotech.moneycontrol.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.app.RxBus;
import com.myniotech.moneycontrol.services.SyncService;
import com.myniotech.moneycontrol.view.fragment.NewSpentFragment;
import com.myniotech.moneycontrol.view.fragment.SpentChartFragment;
import com.myniotech.moneycontrol.view.fragment.SpentOnLineListFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Inject
    SyncService syncService;

    @Inject
    RxBus rxBus;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }


    @OnClick(R.id.button)
    void openList() {
        //replaceFragment(SpentListFragment.getInstance());
        replaceFragment(SpentOnLineListFragment.getInstance());

    }

    @OnClick(R.id.openChart)
    void openChart() {
        replaceFragment(SpentChartFragment.getInstance());
    }

    @OnClick(R.id.newSpent)
    void newSpent() {
        replaceFragment(NewSpentFragment.getInstance());
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MoneyControlApp) getApplication()).getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        replaceFragment(NewSpentFragment.getInstance());

        disposables.add(rxBus
                .toObserverable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Events.UpdateEvent) {
                            Toast.makeText(MainActivity.this, "Start Sync Event", Toast.LENGTH_SHORT).show();
                            syncService.sync();
                        } else if (object instanceof Events.FinishSyncEvent) {
                            Toast.makeText(MainActivity.this, "Finish Sync Event", Toast.LENGTH_SHORT).show();
                            //replaceFragment(NewSpentFragment.getInstance());
                        }
                    }
                }));

        syncService.sync();


    }


}
