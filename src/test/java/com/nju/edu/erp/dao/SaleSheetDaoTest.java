package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CustomerPurchaseAmountPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class SaleSheetDaoTest {

    @Autowired
    SaleSheetDao saleSheetDao;

    @Test
    void testGetMaxAmountCustomerOfSalesmanByTime() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime =dateFormat.parse("2021-10-1 22:36:01");
        Date endTime=dateFormat.parse("2022-5-9 16:13:20");
        CustomerPurchaseAmountPO cp=saleSheetDao.getMaxAmountCustomerOfSalesmanByTime("yzh",beginTime,endTime);
        System.out.println(cp);
    }
}
