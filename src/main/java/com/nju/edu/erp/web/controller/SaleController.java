package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * 销售人员制定销售单
     */
    @Authorized (roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM})
    @PostMapping(value = "/sheet-make")
    public Response makePurchaseOrder(UserVO userVO, @RequestBody SaleSheetVO saleSheetVO)  {
        saleService.makeSaleSheet(userVO, saleSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 根据状态查看销售单
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) SaleSheetState state)  {
        return Response.buildSuccess(saleService.getSaleSheetByState(state));
    }

    /**
     * 销售经理审批
     * @param saleSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @GetMapping(value = "/first-approval")
    @Authorized (roles = {Role.SALE_MANAGER})
    public Response firstApproval(@RequestParam("saleSheetId") String saleSheetId,
                                  @RequestParam("state") SaleSheetState state)  {
        if(state.equals(SaleSheetState.FAILURE) || state.equals(SaleSheetState.PENDING_LEVEL_2)) {
            saleService.approval(saleSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 总经理审批
     * @param saleSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM})
    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("saleSheetId") String saleSheetId,
                                   @RequestParam("state") SaleSheetState state)  {
        if(state.equals(SaleSheetState.FAILURE) || state.equals(SaleSheetState.SUCCESS)) {
            saleService.approval(saleSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }


}
