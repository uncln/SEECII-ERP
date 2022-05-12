package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.po.WarehouseIODetailPO;

import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.model.vo.UserVO;

import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    WarehouseService warehouseService;

    @Test
    void productWarehousing() {
        WarehouseInputFormVO warehouseInputFormVO = new WarehouseInputFormVO();
        warehouseInputFormVO.setOperator("zr");
        List<WarehouseInputFormContentVO> warehouseInputFormContentVOS = new ArrayList<>();
        WarehouseInputFormContentVO warehouseInputFormContentVO = WarehouseInputFormContentVO.builder().productionDate(new Date()).pid("0000000000400000").purchasePrice(null).quantity(2000).remark("oaaa").build();
        WarehouseInputFormContentVO warehouseInputFormContentVO2 = WarehouseInputFormContentVO.builder().productionDate(new Date()).pid("0000000000500000").purchasePrice(null).quantity(1000).remark("paaa").build();
        WarehouseInputFormContentVO warehouseInputFormContentVO3 = WarehouseInputFormContentVO.builder().productionDate(new Date()).pid("0000000000500001").purchasePrice(null).quantity(2000).remark("qaaa").build();
        warehouseInputFormContentVOS.add(warehouseInputFormContentVO);
        warehouseInputFormContentVOS.add(warehouseInputFormContentVO2);
        warehouseInputFormContentVOS.add(warehouseInputFormContentVO3);
        warehouseInputFormVO.setList(warehouseInputFormContentVOS);
//        warehouseService.productWarehousing(warehouseInputFormVO);
    }

    @Test

    void testGetWarehouseIODetailByTime() throws ParseException {

        List<WarehouseIODetailPO> list = warehouseService.getWarehouseIODetailByTime("2021-5-9 22:36:01", "2022-5-9 16:13:20");
        System.out.println(list);
    }

    void approvalInputSheet() {
        warehouseService.approvalInputSheet(UserVO.builder().name("sky").build(),"RKD-20220504-00000", WarehouseInputSheetState.PENDING);
        warehouseService.approvalInputSheet(UserVO.builder().name("sky").build(),"RKD-20220504-00000", WarehouseInputSheetState.SUCCESS);

    }
}