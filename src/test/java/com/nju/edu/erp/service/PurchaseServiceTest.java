package com.nju.edu.erp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseServiceTest {
    @Autowired
    PurchaseService purchaseService;

    @Test
    public void findOneSheetById() {
        purchaseService.getPurchaseSheetById("abcabc");
    }
}