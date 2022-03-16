package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    public WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/input")
    public Response warehouseInput(@RequestBody WarehouseInputFormVO warehouseInputFormVO){
        warehouseService.productWarehousing(warehouseInputFormVO);
        return Response.buildSuccess(null);
    }
}
