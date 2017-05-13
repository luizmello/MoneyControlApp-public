package com.myniotech.moneycontrol.app;

import android.app.Application;
import android.content.Context;

import com.myniotech.moneycontrol.BuildConfig;
import com.myniotech.moneycontrol.presentation.ISpentChartPresenter;
import com.myniotech.moneycontrol.presentation.ISpentListPresenter;
import com.myniotech.moneycontrol.presentation.ISpentOnLineListPresenter;
import com.myniotech.moneycontrol.presentation.ISpentPresenter;
import com.myniotech.moneycontrol.presentation.SpentChartPresenterImpl;
import com.myniotech.moneycontrol.presentation.SpentListPresenterImpl;
import com.myniotech.moneycontrol.presentation.SpentOnLineListPresenterImpl;
import com.myniotech.moneycontrol.presentation.SpentPresenterImpl;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.repository.spent.SpentRepositoryDBFlowImpl;
import com.myniotech.moneycontrol.rest.DeviceService;
import com.myniotech.moneycontrol.rest.SpentService;
import com.myniotech.moneycontrol.services.SyncService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luiz on 19/04/17.
 */
@Module()
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public ISpentRepository provideISpentRepository(RxBus rxBus) {
        return new SpentRepositoryDBFlowImpl(rxBus);
    }

    @Provides
    @Singleton
    public ISpentPresenter provideSpentPresenter(ISpentRepository spentRepository, SpentService spentService) {
        return new SpentPresenterImpl(spentRepository, spentService);
    }

    @Provides
    @Singleton
    public ISpentListPresenter provideSpentListPresenter(ISpentRepository spentRepository) {
        return new SpentListPresenterImpl(spentRepository);
    }

    @Provides
    @Singleton
    public ISpentChartPresenter provideSpentChartPresenter(ISpentRepository spentRepository) {
        return new SpentChartPresenterImpl(spentRepository);
    }


    @Provides
    public OkHttpClient provideLoggingCapableHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public SpentService provideSpentService(Retrofit retrofit) {
        return retrofit.create(SpentService.class);
    }

    @Provides
    @Singleton
    public DeviceService provideDispositivoService(Retrofit retrofit) {
        return retrofit.create(DeviceService.class);
    }

    @Provides
    @Singleton
    public ISpentOnLineListPresenter provideSpentOnLineListPresenter(SpentService spentService, ISpentRepository spentRepository) {
        return new SpentOnLineListPresenterImpl(spentService, spentRepository);
    }

    @Provides
    @Singleton
    public RxBus provideRxBus() {
        return new RxBus();
    }

    @Provides
    @Singleton
    public SyncService provideSyncService(SpentService spentService, ISpentRepository spentRepository, RxBus rxBus) {
        return new SyncService(spentService, spentRepository, rxBus);
    }


}
