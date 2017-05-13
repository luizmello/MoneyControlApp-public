package com.myniotech.moneycontrol.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.app.RxBus;
import com.myniotech.moneycontrol.dto.SpentSync;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.rest.SpentService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luiz on 10/05/17.
 */

public class SyncService extends Service {

    SpentService spentService;

    ISpentRepository spentRepository;

    RxBus rxBus;

    CompositeDisposable compositeDisposable;

    public SyncService() {
    }

    public SyncService(SpentService spentService, ISpentRepository spentRepository, RxBus rxBus) {

        this.spentService = spentService;
        this.spentRepository = spentRepository;
        this.rxBus = rxBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((MoneyControlApp) getApplicationContext()).getApplicationComponent().inject(this);

    }

    public void sync() {

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(spentService.getSpents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SpentSync>() {
                    @Override
                    public void onNext(SpentSync spentSync) {
                        spentRepository.update(spentSync.getSpentList());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }
}
