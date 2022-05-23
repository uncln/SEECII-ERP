package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class WarehouseServiceImplTest {

    @Autowired
    WarehouseService warehouseService;

    @Test
    public void warehouseCounting() {
        System.out.println(warehouseService);
        System.out.println(warehouseService.warehouseCounting());
    }
}