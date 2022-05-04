package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.model.po.WarehouseInputSheetContentPO;
import com.nju.edu.erp.model.po.WarehouseInputSheetPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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
//                        .createTime(new Date())
//                .build());
    }

    @Test
    void getDraftSheets() {
        System.out.println(warehouseInputSheetDao.getDraftSheets(WarehouseInputSheetState.DRAFT));
    }

    @Test
    void updateByIdTest() {
        WarehouseInputSheetPO warehouseInputSheetpo = warehouseInputSheetDao.getSheet("RKD-20220504-00000");
        warehouseInputSheetpo.setState(WarehouseInputSheetState.DRAFT);
        warehouseInputSheetDao.updateById(warehouseInputSheetpo);
    }

    @Test
    void getAllById() {
        List<WarehouseInputSheetContentPO> list = warehouseInputSheetDao.getAllContentById("RKD-20220504-00000");
        System.out.println(list);
    }
}