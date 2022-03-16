package com.nju.edu.erp.CategoryTest;


import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.model.po.ProductPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {

    @Autowired
    ProductDao productDao;

    @Test
    void updateTest() {
        ProductPO productPO = new ProductPO();
        productPO.setId("0000000000500000");
        productPO.setName("华硕电脑");
        productDao.updateById(productPO);
    }
}
