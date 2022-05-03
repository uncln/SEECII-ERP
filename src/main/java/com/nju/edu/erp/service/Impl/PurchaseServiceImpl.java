package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    /**
     * 制定进货单
     *
     * @param purchaseSheetVO 进货单
     */
    @Override
    public void makePurchaseSheet(PurchaseSheetVO purchaseSheetVO) {
        // TODO

    }

    /**
     * 根据状态获取进货单(state == null 则获取所有进货单)
     *
     * @param state 进货单状态
     * @return 进货单
     */
    @Override
    public List<PurchaseSheetVO> getPurchaseSheetByState(PurchaseSheetState state) {
        // TODO
        return null;
    }

    /**
     * 根据进货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param PurchaseSheetId 进货单id
     * @param state           进货单要达到的状态
     */
    @Override
    public void approval(String PurchaseSheetId, PurchaseSheetState state) {
        // TODO

    }
}
