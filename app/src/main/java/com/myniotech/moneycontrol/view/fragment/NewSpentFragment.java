package com.myniotech.moneycontrol.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.MoneyControlApp;
import com.myniotech.moneycontrol.app.RxBus;
import com.myniotech.moneycontrol.model.spent.SpentType;
import com.myniotech.moneycontrol.presentation.ISpentPresenter;
import com.myniotech.moneycontrol.view.activity.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luiz on 20/04/17.
 */

public class NewSpentFragment extends Fragment implements NewSpentFragmentView {


    @Inject
    ISpentPresenter spentPresenter;

    @BindView(R.id.edtSpentValue)
    CurrencyEditText edtSpentValue;

    @BindView(R.id.checkCard)
    ToggleButton tbtCheckCard;

    @BindView(R.id.checkCash)
    ToggleButton tbtCheckCash;

    @BindView(R.id.txtTotalSpent)
    TextView txtTotalSpent;

    @BindView(R.id.txtTotalSpentCC)
    TextView txtTotalSpentCC;

    @BindView(R.id.txtTotalSpentCash)
    TextView txtTotalSpentCash;

    @OnClick(R.id.btnSaveSpent)
    void saveSpent() {

        spentPresenter.saveLocalSpent();

    }

    @OnClick(R.id.checkCash)
    void onClickCheckCash() {

        tbtCheckCard.setChecked(false);
    }

    @OnClick(R.id.checkCard)
    void onClickCheckCard() {
        tbtCheckCash.setChecked(false);
    }

    private String current = "";

    @Inject
    RxBus rxBus;

    private final CompositeDisposable disposables = new CompositeDisposable();


    public static NewSpentFragment getInstance() {
        return new NewSpentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MoneyControlApp) getActivity().getApplication()).getApplicationComponent().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.new_spent_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        spentPresenter.setView(this);

        tbtCheckCash.setChecked(true);

        spentPresenter.updateView();

        disposables.add(rxBus
                .toObserverable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Events.FinishSyncEvent) {
                            //Toast.makeText(getContext(), "Finish Sync Event", Toast.LENGTH_SHORT).show();
                            spentPresenter.updateView();
                        }
                    }
                }));

    }

    @Override
    public String getSpentValue() {

        return String.valueOf(edtSpentValue.getRawValue());
    }

    @Override
    public SpentType getSpentType() {

        if (tbtCheckCard.isChecked()) {
            return SpentType.CREDIT_CARD;
        } else if (tbtCheckCash.isChecked()) {
            return SpentType.MONEY;
        }

        return null;

    }

    @Override
    public void setTotalSpent(String totalSpent) {

        txtTotalSpent.setText(totalSpent);

    }

    @Override
    public void setTotalSpentWithCC(String totalSpentWithCC) {

        txtTotalSpentCC.setText(totalSpentWithCC);

    }

    @Override
    public void setTotalSpentWithCash(String totalSpentWithCash) {

        txtTotalSpentCash.setText(totalSpentWithCash);
    }


    @Override
    public void showSpentSavedLocalMessage() {
        Toast.makeText(getContext(), "Spent Saved On Device", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorOnSaveLocalSpent() {
        Toast.makeText(getContext(), "Error on save spent, value can't be empty", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSpentSavedCloudMessage() {
        Toast.makeText(getContext(), "Spent Saved On Server", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorOnSaveCloudSpent() {
        Toast.makeText(getContext(), "Can't save Spent on Server", Toast.LENGTH_SHORT).show();
    }

}
