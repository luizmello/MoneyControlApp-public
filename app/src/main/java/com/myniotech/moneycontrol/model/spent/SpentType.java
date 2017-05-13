package com.myniotech.moneycontrol.model.spent;

/**
 * Created by luiz on 20/04/17.
 */

public enum SpentType {

    MONEY("money"), CREDIT_CARD("credit card");

    private String title;

    SpentType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
