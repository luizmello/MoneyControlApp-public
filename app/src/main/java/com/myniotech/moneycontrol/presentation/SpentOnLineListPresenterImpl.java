package com.myniotech.moneycontrol.presentation;

import android.util.Log;

import com.myniotech.moneycontrol.dto.SpentSync;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.rest.SpentService;
import com.myniotech.moneycontrol.view.fragment.SpentOnLineListFragmentView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by luiz on 25/04/17.
 */

public class SpentOnLineListPresenterImpl implements ISpentOnLineListPresenter {


    private SpentService spentService;
    private ISpentRepository spentRepository;
    private SpentOnLineListFragmentView view;

    CompositeDisposable compositeDisposable;


    public SpentOnLineListPresenterImpl(SpentService spentService, ISpentRepository spentRepository) {

        this.spentService = spentService;
        this.spentRepository = spentRepository;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(SpentOnLineListFragmentView view) {

        this.view = view;
    }


    private DisposableObserver<SpentSync> observerSpent() {

        return new DisposableObserver<SpentSync>() {


            @Override
            public void onNext(SpentSync spentSync) {
                if (spentSync.getSpentList() != null | !spentSync.getSpentList().isEmpty()) {
                    view.setSpentList(spentSync.getSpentList());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("OnError", e.toString());

            }

            @Override
            public void onComplete() {

            }
        };
    }


    @Override
    public void loadSpentList() {

        compositeDisposable.add(spentService.getSpents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observerSpent()));

    }

    @Override
    public void deleteSpent(Spent spent) {


        compositeDisposable.add(spentService.deleteSpent(spent.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {

                    @Override
                    public void onNext(Response<ResponseBody> responseBody) {

                        if (responseBody.code() == 200) {

                            view.showDeleteStatus("Spent Deleted");
                            //delete local
                            spentRepository.delete(spent);

                        } else {
                            view.showDeleteStatus("Can't delete now");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        loadSpentList();

                    }
                }));
    }

}
