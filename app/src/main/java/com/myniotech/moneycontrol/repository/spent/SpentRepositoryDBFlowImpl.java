package com.myniotech.moneycontrol.repository.spent;

import com.myniotech.moneycontrol.app.Events;
import com.myniotech.moneycontrol.app.RxBus;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.model.spent.SpentType;
import com.myniotech.moneycontrol.model.spent.Spent_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by luiz on 20/04/17.
 */

public class SpentRepositoryDBFlowImpl implements ISpentRepository {

    public SpentRepositoryDBFlowImpl(RxBus rxBus) {
        this.rxBus = rxBus;
    }

    RxBus rxBus;

    @Override
    public List<Spent> getAll() {
        return SQLite.select().from(Spent.class).orderBy(Spent_Table.created_at, true).queryList();
    }

    @Override
    public List<Spent> getAllCC() {
        return SQLite.select().from(Spent.class).where(Spent_Table.spentType.eq(SpentType.CREDIT_CARD)).queryList();
    }

    @Override
    public List<Spent> getAllCash() {
        return SQLite.select().from(Spent.class).where(Spent_Table.spentType.eq(SpentType.MONEY)).queryList();
    }

    @Override
    public void save(Spent spent) {
        spent.save();
    }

    @Override
    public void delete(Spent spent) {
        spent.delete();
    }

    @Override
    public void update(List<Spent> spentList) {

        List<Spent> localSpents = this.getAll();

        //remove local
        for (Spent s : localSpents) {
            if (!spentList.contains(s)) {
                this.delete(s);
            }
        }

        for (Spent s : spentList) {
            if (!localSpents.contains(s)) {
                this.save(s);
            } else {
                s.update();
                this.save(s);
            }
        }

        rxBus.send(new Events.FinishSyncEvent());

    }
}
