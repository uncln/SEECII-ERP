package com.nju.edu.erp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class WarehouseOutputSheetDaoTest {
    @Autowired
    WarehouseOutputSheetDao warehouseOutputSheetDao;

    /**
     * 库存查看:一个时间段内的入库数量合计
     * @throws ParseException
     */
    @Test
    void testGetWarehouseOutputProductQuantityByTime() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime =dateFormat.parse("2021-10-1 22:36:01");
        Date endTime=dateFormat.parse("2022-5-9 16:13:20");
        int quantity=warehouseOutputSheetDao.getWarehouseOutputProductQuantityByTime(beginTime,endTime);
    }
}
