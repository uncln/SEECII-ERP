package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.sheetState.PurchaseReturnsSheetState;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.service.PurchaseReturnsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseReturnsServiceImplTest {

    @Autowired
    PurchaseReturnsService service;

    @Test
    void makePurchaseReturnsSheet() {
        service.makePurchaseReturnsSheet(UserVO.builder()
                .name("uncln").build(), PurchaseReturnsSheetVO.builder()
                        .purchaseSheetId("JHD-20220504-00000")
                        .purchaseReturnsSheetContent(
                                Collections.singletonList(PurchaseReturnsSheetContentVO.builder()
                                        .pid("0000000000500000")
                                        .quantity(5)
                                        .build())
                        )
                        .createTime(new Date())
                .build());
    }

    @Test
    void getPurchaseReturnsSheetByState() {
        System.out.println(service.getPurchaseReturnsSheetByState(null));
    }

    @Test
    void approval() {
//        service.approval("JHTHD-20220511-00000", PurchaseReturnsSheetState.PENDING_LEVEL_2);
        service.approval("JHTHD-20220511-00000", PurchaseReturnsSheetState.SUCCESS);
    }
}