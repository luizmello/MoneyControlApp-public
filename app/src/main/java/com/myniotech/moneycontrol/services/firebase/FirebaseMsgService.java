package com.myniotech.moneycontrol.services.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.app.RxBus;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by luiz on 09/05/17.
 */

public class FirebaseMsgService extends FirebaseMessagingService {

    private static final String TAG = "fcm_remote_msg";


    @Inject
    RxBus rxBus;

    @Override
    public void onCreate() {
        super.onCreate();
        ((MoneyControlApp) getApplication()).getApplicationComponent().inject(this);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> mensagem = remoteMessage.getData();
        Log.i(TAG, String.valueOf(mensagem));

        rxBus.send(new Events.UpdateEvent());

    }
}
