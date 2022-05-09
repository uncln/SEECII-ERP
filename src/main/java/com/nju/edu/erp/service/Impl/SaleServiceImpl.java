package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleSheetDao saleSheetDao;

    private final ProductDao productDao;

    private final CustomerDao customerDao;

    @Autowired
    public SaleServiceImpl(SaleSheetDao saleSheetDao, ProductDao productDao, CustomerDao customerDao) {
        this.saleSheetDao = saleSheetDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO) {
        SaleSheetPO saleSheetPO = new SaleSheetPO();
        BeanUtils.copyProperties(saleSheetVO, saleSheetPO);
        // 设置操作员和时间
        saleSheetPO.setOperator(userVO.getName());
        saleSheetPO.setCreateTime(new Date());
        // 获取id并设置
        SaleSheetPO latestSheet = saleSheetDao.getLatestSheet();
        String sheetId = IdGenerator.generateSheetId(latestSheet == null ? null : latestSheet.getId(), "XSD");
        saleSheetPO.setId(sheetId);
        saleSheetPO.setState(SaleSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContentPOList = new ArrayList<>();
        for (SaleSheetContentVO content : saleSheetVO.getSaleSheetContent()) {
            SaleSheetContentPO sContentPO = new SaleSheetContentPO();
            BeanUtils.copyProperties(content, sContentPO);
            BigDecimal unitPrice = sContentPO.getUnitPrice();
            if (unitPrice == null) {
                ProductPO productPO = productDao.findById(content.getPid());
                unitPrice = productPO.getPurchasePrice();
                sContentPO.setUnitPrice(unitPrice);
            }
            sContentPO.setSaleSheetId(sheetId);
            sContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(sContentPO.getQuantity())));
            saleSheetContentPOList.add(sContentPO);
            totalAmount = totalAmount.add(sContentPO.getTotalPrice());
        }
        if (saleSheetPO.getSalesman() == null) {
            saleSheetPO.setSalesman(customerDao.findOneById(saleSheetPO.getSupplier()).getName());
        }
        saleSheetPO.setRawTotalAmount(totalAmount);
        saleSheetPO.setFinalAmount(totalAmount.multiply(saleSheetPO.getDiscount()));
        saleSheetDao.saveBatchSheetContent(saleSheetContentPOList);
        saleSheetDao.saveSheet(saleSheetPO);
    }
}
