package com.myniotech.moneycontrol.services.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.rest.DeviceService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luiz on 09/05/17.
 */

public class MoneyControlInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FCM TOKEN";

    @Inject
    DeviceService deviceService;

    @Override
    public void onCreate() {
        super.onCreate();
        ((MoneyControlApp) getApplication()).getApplicationComponent().inject(this);

    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {

        Call<Void> call = deviceService.enviaToken(refreshedToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("token enviado", refreshedToken);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("token falhou", t.getMessage());
            }
        });
    }
}
