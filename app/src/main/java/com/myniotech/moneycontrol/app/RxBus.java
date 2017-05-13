package com.myniotech.moneycontrol.app;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    public RxBus() {
    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}