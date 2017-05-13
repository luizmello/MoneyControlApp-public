package com.myniotech.moneycontrol.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by luiz on 19/04/17.
 */

public class MoneyControlApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {

        super.onCreate();


        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());


        JodaTimeAndroid.init(this);


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Stetho.initializeWithDefaults(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
