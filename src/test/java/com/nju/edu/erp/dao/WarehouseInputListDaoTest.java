package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseInputListPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseInputListDaoTest {

    @Autowired
    WarehouseInputListDao warehouseInputListDao;

    @Test
    void saveBatch() {
        List<WarehouseInputListPO> warehouseInputListPOS = new ArrayList<>();
        WarehouseInputListPO warehouseInputListPO = new WarehouseInputListPO
                (null,"123123","123",321, BigDecimal.valueOf(1.23),new Date(),"hahahaha");
        warehouseInputListPOS.add(warehouseInputListPO);
        warehouseInputListPOS.add(warehouseInputListPO);
        warehouseInputListPOS.add(warehouseInputListPO);
//        warehouseInputListDao.saveBatch(warehouseInputListPOS);
    }
}