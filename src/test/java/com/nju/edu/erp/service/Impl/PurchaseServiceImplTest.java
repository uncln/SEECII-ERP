package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PurchaseSheetDao;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseServiceImplTest {

    @Autowired
    PurchaseService purchaseService;

    @Test
    void makePurchaseSheet() {
        purchaseService.makePurchaseSheet(PurchaseSheetVO.builder()
                        .purchaseSheetContent(
                                Arrays.asList(PurchaseSheetContentVO.builder().pid("0000000000400000").quantity(10).build(),
                                        PurchaseSheetContentVO.builder().pid("0000000000400001").unitPrice(BigDecimal.valueOf(2000)).quantity(20).build(),
                                        PurchaseSheetContentVO.builder().pid("0000000000500000").unitPrice(BigDecimal.valueOf(5000)).quantity(30).build())
                        )
                        .remark("asd...")
                        .operator("uncln")
                        .supplier(1)
                .build());
    }

    @Test
    void getPurchaseSheetByState() {
        System.out.println(purchaseService.getPurchaseSheetByState(PurchaseSheetState.PENDING_LEVEL_1));
    }

    @Test
    void approval() {
        purchaseService.approval("JHD-20220504-00000", PurchaseSheetState.SUCCESS);
    }
}