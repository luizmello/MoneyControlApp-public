package com.myniotech.moneycontrol;

import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.model.spent.SpentFactory;
import com.myniotech.moneycontrol.model.spent.SpentType;
import com.myniotech.moneycontrol.repository.spent.SpentRepositoryDBFlowImpl;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //@Test
//    public void testSpentWithMoney() {
//
//        Spent spent = SpentFactory.newSpent(SpentType.MONEY, new BigDecimal(100), Calendar.getInstance().getTimeInMillis());
//        System.out.println(spent.getSpentType());
//        System.out.println(spent.getSpentValue());
//        System.out.println(spent.getSpentDate());
//
//    }
//
//    @Test
//    public void testSpentWithCreditCard() {
//
//        Spent spent = SpentFactory.newSpent(SpentType.CREDIT_CARD, new BigDecimal(100), Calendar.getInstance().getTimeInMillis());
//        System.out.println(spent.toString());
//
//
//    }
//
//    @Test
//    public void testSaveSpentWithCreditCard() {
//
//
//        Spent spent = SpentFactory.newSpent(SpentType.CREDIT_CARD, new BigDecimal(100), Calendar.getInstance().getTimeInMillis());
//
//        SpentRepositoryDBFlowImpl spentRepository = new SpentRepositoryDBFlowImpl();
//
//        spentRepository.save(spent);
//
//    }


}