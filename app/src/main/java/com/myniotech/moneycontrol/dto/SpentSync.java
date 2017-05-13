package com.myniotech.moneycontrol.dto;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.rest.template.SpentTemplate;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luiz on 04/05/17.
 */
public class SpentSync {

    private final List<SpentTemplate> spentList;
    private final String updated_at;

    public SpentSync(List<SpentTemplate> spentList, String updated_at) {
        this.spentList = spentList;
        this.updated_at = updated_at;
    }

    public List<Spent> getSpentList() {

        List<Spent> spents = new ArrayList<>();

        for (SpentTemplate s :
                spentList) {

            spents.add(new Spent(s.getId(),
                    s.getSpentType(),
                    s.getValue(),
                    LocalDateTime.parse(s.getCreated_at())));

        }

        return spents;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
