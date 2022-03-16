package com.nju.edu.erp.CategoryTest;

import com.nju.edu.erp.service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTests {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    void createCategoryTest() {
        categoryService.createCategory(4, "笔记本");

    }
}
