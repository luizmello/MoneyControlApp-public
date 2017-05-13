package com.myniotech.moneycontrol.model.spent;

import com.myniotech.moneycontrol.converter.LocalDateTimeConverter;
import com.myniotech.moneycontrol.repository.spent.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by luiz on 20/04/17.
 */

@Table(database = AppDatabase.class)
public class Spent extends BaseModel {

    @PrimaryKey
    String id;
    @Column
    SpentType spentType;
    @Column
    BigDecimal value;
    @Column(typeConverter = LocalDateTimeConverter.class)
    LocalDateTime created_at;

    public Spent() {
    }

    public Spent(SpentType spentType, BigDecimal value, LocalDateTime created_at) {
        this.id = UUID.randomUUID().toString();
        this.spentType = spentType;
        this.value = value;
        this.created_at = created_at;
    }

    public Spent(String id, SpentType spentType, BigDecimal value, LocalDateTime created_at) {
        this.id = id;
        this.spentType = spentType;
        this.value = value;
        this.created_at = created_at;
    }

    public SpentType getSpentType() {
        return spentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSpentType(SpentType spentType) {
        this.spentType = spentType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Spent{" +
                "id='" + id + '\'' +
                ", spentType=" + spentType +
                ", value=" + value +
                ", created_at=" + created_at +
                '}';
    }
}
