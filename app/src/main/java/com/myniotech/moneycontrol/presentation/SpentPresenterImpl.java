package com.myniotech.moneycontrol.presentation;

import com.myniotech.moneycontrol.app.Utils;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.repository.spent.ISpentRepository;
import com.myniotech.moneycontrol.rest.SpentService;
import com.myniotech.moneycontrol.view.fragment.NewSpentFragmentView;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentPresenterImpl implements ISpentPresenter {

    private NewSpentFragmentView newSpentFragmentView;
    private ISpentRepository spentRepository;
    private SpentService spentService;
    private Spent spent;

    private CompositeDisposable compositeDisposable;

    public SpentPresenterImpl(ISpentRepository spentRepository, SpentService spentService) {
        this.spentRepository = spentRepository;
        this.spentService = spentService;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(NewSpentFragmentView view) {
        this.newSpentFragmentView = view;
    }

    @Override
    public void saveLocalSpent() {

        if (!newSpentFragmentView.getSpentValue().contentEquals("0")) {

            try {

                String cleanString = newSpentFragmentView.getSpentValue();

                BigDecimal value = new BigDecimal(cleanString);

                Spent spent = new Spent(
                        newSpentFragmentView.getSpentType(),
                        value,
                        LocalDateTime.now());

                spentRepository.save(spent);

                this.spent = spent;


                newSpentFragmentView.showSpentSavedLocalMessage();


                updateView();

            } catch (Exception e) {

                newSpentFragmentView.showErrorOnSaveLocalSpent();
            }
        } else {
            newSpentFragmentView.showErrorOnSaveLocalSpent();
        }

        saveCloudSpent();


    }

    @Override
    public void loadTotalSpent() {

        List<Spent> spentList = spentRepository.getAll();

        BigDecimal totalSpent = new BigDecimal("0");

        for (Spent spent :
                spentList) {
            totalSpent = totalSpent.add(spent.getValue());
        }

        newSpentFragmentView.setTotalSpent(Utils.longToCurrency(totalSpent.longValue()));
    }

    @Override
    public void loadTotalSpentWithCC() {

        List<Spent> spentList = spentRepository.getAllCC();

        BigDecimal totalSpent = new BigDecimal("0");

        for (Spent spent :
                spentList) {
            totalSpent = totalSpent.add(spent.getValue());
        }

        newSpentFragmentView.setTotalSpentWithCC(Utils.longToCurrency(totalSpent.longValue()));

    }

    @Override
    public void loadTotalSpentWithCash() {

        List<Spent> spentList = spentRepository.getAllCash();

        BigDecimal totalSpent = new BigDecimal("0");

        for (Spent spent :
                spentList) {
            totalSpent = totalSpent.add(spent.getValue());
        }

        newSpentFragmentView.setTotalSpentWithCash(Utils.longToCurrency(totalSpent.longValue()));

    }

    @Override
    public void updateView() {

        loadTotalSpent();
        loadTotalSpentWithCash();
        loadTotalSpentWithCC();

    }

    @Override
    public void saveCloudSpent() {

        compositeDisposable.add(responseObservable(spent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(responseDisposableObserver()));
    }

    private Observable<Response<ResponseBody>> responseObservable(Spent spent) {

        return spentService.postSpent(spent);
    }

    private DisposableObserver<Response<ResponseBody>> responseDisposableObserver() {
        return new DisposableObserver<Response<ResponseBody>>() {
            @Override
            public void onNext(Response<ResponseBody> response) {

                if (response.code() == 200) {
                    newSpentFragmentView.showSpentSavedCloudMessage();

                } else {
                    newSpentFragmentView.showErrorOnSaveCloudSpent();
                }

            }

            @Override
            public void onError(Throwable e) {
                newSpentFragmentView.showErrorOnSaveCloudSpent();
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
