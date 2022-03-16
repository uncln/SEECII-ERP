package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormListVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    WarehouseService warehouseService;

    @Test
    void productWarehousing() {
        WarehouseInputFormVO warehouseInputFormVO = new WarehouseInputFormVO();
        warehouseInputFormVO.setOperator("zxr");
        List<WarehouseInputFormListVO> warehouseInputFormListVOS = new ArrayList<>();
        WarehouseInputFormListVO warehouseInputFormListVO = WarehouseInputFormListVO.builder().productionDate(new Date()).pid("0000000000400000").purchasePrice(BigDecimal.valueOf(3125)).quantity(1000).remark("oooooooo").build();
        WarehouseInputFormListVO warehouseInputFormListVO2 = WarehouseInputFormListVO.builder().productionDate(new Date()).pid("0000000000500000").purchasePrice(null).quantity(1500).remark("pppppppp").build();
        WarehouseInputFormListVO warehouseInputFormListVO3 = WarehouseInputFormListVO.builder().productionDate(new Date()).pid("0000000000500001").purchasePrice(null).quantity(2000).remark("qqqqqqq").build();
        warehouseInputFormListVOS.add(warehouseInputFormListVO);
        warehouseInputFormListVOS.add(warehouseInputFormListVO2);
        warehouseInputFormListVOS.add(warehouseInputFormListVO3);
        warehouseInputFormVO.setList(warehouseInputFormListVOS);
//        warehouseService.productWarehousing(warehouseInputFormVO);
    }
}