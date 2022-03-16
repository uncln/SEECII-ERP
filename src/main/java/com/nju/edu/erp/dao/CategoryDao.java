package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.CategoryPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryDao {

    int createCategory(CategoryPO categoryPO);

    // CategoryPO findByCategoryId(Integer categoryId);
}
