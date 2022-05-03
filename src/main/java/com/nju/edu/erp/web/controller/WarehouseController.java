package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.warehouse.GetWareProductInfoParamsVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    public WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/input")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseInput(@RequestBody WarehouseInputFormVO warehouseInputFormVO){
        log.info(warehouseInputFormVO.toString());
        warehouseService.productWarehousing(warehouseInputFormVO);
        return Response.buildSuccess();
    }

    @PostMapping("/output")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseOutput(@RequestBody WarehouseOutputFormVO warehouseOutputFormVO){
        log.info(warehouseOutputFormVO.toString());
        warehouseService.productOutOfWarehouse(warehouseOutputFormVO);
        return Response.buildSuccess();
    }

    @PostMapping("/product/count")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseOutput(@RequestBody GetWareProductInfoParamsVO getWareProductInfoParamsVO){
        return Response.buildSuccess(warehouseService.getWareProductInfo(getWareProductInfoParamsVO));
    }
}
