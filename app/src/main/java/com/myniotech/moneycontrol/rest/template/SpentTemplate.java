package com.myniotech.moneycontrol.rest.template;


import com.myniotech.moneycontrol.model.spent.SpentType;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

/**
 * Created by luiz on 05/05/17.
 */

public class SpentTemplate {

    String id;
    SpentType spentType;
    BigDecimal value;
    String created_at;

    public SpentTemplate(String id, SpentType spentType, BigDecimal value, String created_at) {
        this.id = id;
        this.spentType = spentType;
        this.value = value;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public SpentType getSpentType() {
        return spentType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCreated_at() {
        return created_at;
    }
}
