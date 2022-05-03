package com.nju.edu.erp.enums.sheetState;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseInputSheetStateTest {

    @Test
    void testToString() {
        System.out.println(WarehouseInputSheetState.SUCCESS);
        System.out.println(WarehouseInputSheetState.SUCCESS.name());
    }
}