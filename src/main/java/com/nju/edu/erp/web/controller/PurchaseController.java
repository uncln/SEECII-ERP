package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.service.PurchaseService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * 销售人员制定进货单
     */
    @Authorized (roles = {Role.FINANCIAL_STAFF, Role.SALE_MANAGER, Role.GM})
    @PostMapping(value = "/sheet-make")
    public Response makePurchaseOrder(@RequestBody PurchaseSheetVO purchaseSheetVO)  {
        purchaseService.makePurchaseSheet(purchaseSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 销售经理审批
     * @param purchaseSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @GetMapping(value = "/first-approval")
    @Authorized (roles = {Role.INVENTORY_MANAGER})
    public Response firstApproval(@RequestParam("purchaseSheetId") String purchaseSheetId,
                                  @RequestParam("state") PurchaseSheetState state)  {
        if(state.equals(PurchaseSheetState.FAILURE) || state.equals(PurchaseSheetState.PENDING_LEVEL_2)) {
            purchaseService.approval(purchaseSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 总经理审批
     * @param purchaseSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM})
    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("purchaseSheetId") String purchaseSheetId,
                                   @RequestParam("state") PurchaseSheetState state)  {
        if(state.equals(PurchaseSheetState.FAILURE) || state.equals(PurchaseSheetState.SUCCESS)) {
            purchaseService.approval(purchaseSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 根据状态查看销售单
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) PurchaseSheetState state)  {
        return Response.buildSuccess(purchaseService.getPurchaseSheetByState(state));
    }

}
