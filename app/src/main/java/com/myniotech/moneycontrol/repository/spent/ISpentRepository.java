package com.myniotech.moneycontrol.repository.spent;

import com.myniotech.moneycontrol.model.spent.Spent;

import java.util.List;

/**
 * Created by luiz on 20/04/17.
 */

public interface ISpentRepository {

    List<Spent> getAll();
    List<Spent> getAllCC();
    List<Spent> getAllCash();
    void save(Spent spent);
    void delete(Spent spent);

    void update(List<Spent> spentList);
}
