package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseInputPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class WarehouseInputDaoTest {

    @Autowired
    WarehouseInputDao warehouseInputDao;

    @Test
    void getLatest() {
        System.out.println(warehouseInputDao.getLatest());
    }

    @Test
    void save() {
//        System.out.println(warehouseInputDao.save(WarehouseInputPO.builder().id("adasdasd").batchId(2).operator("lxs").updateTime(new Date()).build()));
    }
}