package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.model.po.WarehouseIODetailPO;
import com.nju.edu.erp.model.po.WarehouseInputSheetContentPO;
import com.nju.edu.erp.model.po.WarehouseInputSheetPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    void getWarehouseIODetailByTime() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime =dateFormat.parse("2021-10-1 22:36:01");
        Date endTime=dateFormat.parse("2022-5-9 16:13:20");
        List<WarehouseIODetailPO> list=warehouseInputSheetDao.getWarehouseIODetailByTime(beginTime,endTime);
        System.out.println(list);
    }
}