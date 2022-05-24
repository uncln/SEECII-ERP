package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.SaleSheetContentPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.utils.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class SaleServiceTest {

    @Autowired
    SaleService saleService;

    @Autowired
    SaleSheetDao saleSheetDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void makeSaleSheet() { // 测试销售单是否生成成功
        UserVO userVO = UserVO.builder()
                .name("xiaoshoujingli")
                .role(Role.SALE_MANAGER)
                .build();

        List<SaleSheetContentVO> saleSheetContentVOS = new ArrayList<>();
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000400000")
                .quantity(50)
                .remark("Test1-product1")
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000400001")
                .quantity(60)
                .remark("Test1-product2")
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleSheetVO saleSheetVO = SaleSheetVO.builder()
                .saleSheetContent(saleSheetContentVOS)
                .supplier(2)
                .discount(BigDecimal.valueOf(0.8))
                .voucherAmount(BigDecimal.valueOf(300))
                .remark("Test1")
                .build();
        SaleSheetPO prevSheet = saleSheetDao.getLatestSheet();
        String realSheetId = IdGenerator.generateSheetId(prevSheet == null ? null : prevSheet.getId(), "XSD");

        saleService.makeSaleSheet(userVO, saleSheetVO);
        SaleSheetPO latestSheet = saleSheetDao.getLatestSheet();
        Assertions.assertNotNull(latestSheet);
        Assertions.assertEquals(realSheetId, latestSheet.getId());
        Assertions.assertEquals(0, latestSheet.getRawTotalAmount().compareTo(BigDecimal.valueOf(412000.00)));
        Assertions.assertEquals(0, latestSheet.getFinalAmount().compareTo(BigDecimal.valueOf(329300.00)));
        Assertions.assertEquals(SaleSheetState.PENDING_LEVEL_1, latestSheet.getState());

        String sheetId = latestSheet.getId();
        Assertions.assertNotNull(sheetId);
        List<SaleSheetContentPO> content = saleSheetDao.findContentBySheetId(sheetId);
        content.sort(Comparator.comparing(SaleSheetContentPO::getPid));
        Assertions.assertEquals(2, content.size());
        Assertions.assertEquals("0000000000400000", content.get(0).getPid());
        Assertions.assertEquals(0, content.get(0).getTotalPrice().compareTo(BigDecimal.valueOf(160000.00)));
        Assertions.assertEquals("0000000000400001", content.get(1).getPid());
        Assertions.assertEquals(0, content.get(1).getTotalPrice().compareTo(BigDecimal.valueOf(252000.00)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void getSaleSheetByState() { // 测试按照状态获取销售单及其content是否成功
        List<SaleSheetVO> saleSheetByState = saleService.getSaleSheetByState(SaleSheetState.PENDING_LEVEL_2);
        Assertions.assertNotNull(saleSheetByState);
        Assertions.assertEquals(1, saleSheetByState.size());
        SaleSheetVO sheet1 = saleSheetByState.get(0);
        Assertions.assertNotNull(sheet1);
        Assertions.assertEquals("XSD-20220524-00003", sheet1.getId());

        List<SaleSheetContentVO> sheet1Content = sheet1.getSaleSheetContent();
        Assertions.assertNotNull(sheet1Content);
        Assertions.assertEquals(2, sheet1Content.size());
        sheet1Content.sort(Comparator.comparing(SaleSheetContentVO::getPid));
        Assertions.assertEquals("0000000000400000", sheet1Content.get(0).getPid());
        Assertions.assertEquals(0, sheet1Content.get(0).getTotalPrice().compareTo(BigDecimal.valueOf(280000.00)));
        Assertions.assertEquals("0000000000400001", sheet1Content.get(1).getPid());
        Assertions.assertEquals(0, sheet1Content.get(1).getTotalPrice().compareTo(BigDecimal.valueOf(380000.00)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void approval() {

    }
}