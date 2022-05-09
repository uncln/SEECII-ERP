package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {


    void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO);
}
