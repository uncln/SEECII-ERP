package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.WarehouseInputSheetPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.warehouse.GetWareProductInfoParamsVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOneProductInfoVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;

import java.util.List;

public interface WarehouseService {
    /**
     * 商品入库
     * @param warehouseInputFormVO 入库单
     */
    void productWarehousing(WarehouseInputFormVO warehouseInputFormVO);

    /**
     * 商品出库
     * @param warehouseOutputFormListVO 出库单
     */
    void productOutOfWarehouse(WarehouseOutputFormVO warehouseOutputFormListVO);

    /**
     * 通过商品id、批次和数量来从库存中取物品
     * @param params 商品id、批次和数量
     * @return 不同批次的相同物品列表
     */
    List<WarehouseOneProductInfoVO> getWareProductInfo(GetWareProductInfoParamsVO params);

    /**
     * 审批入库单(仓库管理员进行确认/总经理进行审批)
     * @param warehouseInputSheetId 入库单id
     * @param state 入库单修改后的状态(state == "待审批"/"审批失败"/"审批完成")
     */
    void approval(UserVO user, String warehouseInputSheetId, WarehouseInputSheetState state);

    /**
     * 通过状态获取入库单(state == null 时获取全部入库单)
     * @param state 入库单状态
     * @return 入库单
     */
    List<WarehouseInputSheetPO> getWareHouseInputSheetByState(WarehouseInputSheetState state);
}
