package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.model.po.WarehouseInputSheetPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseInputSheetDaoTest {
    @Autowired
    WarehouseInputSheetDao warehouseInputSheetDao;

    @Test
    void getLatest() {
        System.out.println(warehouseInputSheetDao.getLatest());
    }

    @Test
    void save() {
//        warehouseInputSheetDao.save(WarehouseInputSheetPO.builder()
//                        .id("zbc")
//                        .purchaseSheetId("bbb")
//                        .operator("ccc")
//                        .batchId(123123)
//                        .state(WarehouseInputSheetState.DRAFT)
//                        .updateTime(new Date())
//                .build());
    }
}