package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PurchaseSheetDao;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.po.PurchaseSheetContentPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.service.PurchaseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    PurchaseSheetDao purchaseSheetDao;

    @Autowired
    public PurchaseServiceImpl(PurchaseSheetDao purchaseSheetDao) {
        this.purchaseSheetDao = purchaseSheetDao;
    }

    /**
     * 制定进货单
     *
     * @param purchaseSheetVO 进货单
     */
    @Override
    @Transactional
    public void makePurchaseSheet(PurchaseSheetVO purchaseSheetVO) {
        PurchaseSheetPO purchaseSheetPO = new PurchaseSheetPO();
        BeanUtils.copyProperties(purchaseSheetVO, purchaseSheetPO);
        PurchaseSheetPO latest = purchaseSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "JHD");
        purchaseSheetPO.setId(id);
        purchaseSheetPO.setState(PurchaseSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<PurchaseSheetContentPO> pContentPOList = new ArrayList<>();
        for(PurchaseSheetContentVO content : purchaseSheetVO.getPurchaseSheetContent()) {
            PurchaseSheetContentPO pContentPO = new PurchaseSheetContentPO();
            BeanUtils.copyProperties(content,pContentPO);
            pContentPO.setPurchaseSheetId(id);
            pContentPO.setTotalPrice(pContentPO.getUnitPrice().multiply(BigDecimal.valueOf(pContentPO.getQuantity())));
            pContentPOList.add(pContentPO);
            totalAmount = totalAmount.add(pContentPO.getTotalPrice());
        }
        purchaseSheetDao.saveBatch(pContentPOList);
        purchaseSheetPO.setTotalAmount(totalAmount);
        purchaseSheetDao.save(purchaseSheetPO);
    }

    /**
     * 根据状态获取进货单[不包括content信息](state == null 则获取所有进货单)
     *
     * @param state 进货单状态
     * @return 进货单
     */
    @Override
    public List<PurchaseSheetVO> getPurchaseSheetByState(PurchaseSheetState state) {
        List<PurchaseSheetVO> res = new ArrayList<>();
        List<PurchaseSheetPO> all;
        if(state == null) {
            all = purchaseSheetDao.findAll();
        } else {
            all = purchaseSheetDao.findAllByState(state);
        }
        for(PurchaseSheetPO po: all) {
            PurchaseSheetVO vo = new PurchaseSheetVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
        }
        return res;
    }

    /**
     * 根据进货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param purchaseSheetId 进货单id
     * @param state           进货单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String purchaseSheetId, PurchaseSheetState state) {
        if(state.equals(PurchaseSheetState.FAILURE)) {
            PurchaseSheetPO purchaseSheet = purchaseSheetDao.findOneById(purchaseSheetId);
            if(purchaseSheet.getState() == PurchaseSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = purchaseSheetDao.updateState(purchaseSheetId, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            PurchaseSheetState prevState;
            if(state.equals(PurchaseSheetState.SUCCESS)) {
                prevState = PurchaseSheetState.PENDING_LEVEL_2;
            } else if(state.equals(PurchaseSheetState.PENDING_LEVEL_2)) {
                prevState = PurchaseSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = purchaseSheetDao.updateStateV2(purchaseSheetId, prevState, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
            if(state.equals(PurchaseSheetState.SUCCESS)) {
                // TODO 审批完成, 修改一系列状态
                // 更新商品表的最新进价
                // 更新客户表(更新应收字段)
                // 制定入库单草稿(在这里关联进货单)
            }
        }
    }
}
