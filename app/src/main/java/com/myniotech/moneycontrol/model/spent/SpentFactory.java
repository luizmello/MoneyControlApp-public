package com.myniotech.moneycontrol.model.spent;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentFactory {

    public static Spent newSpent(SpentType spentType, BigDecimal value, LocalDateTime date) {
        return new Spent(spentType, value, date);
    }



}
