package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductDao {

    int updateById(ProductPO productPO);
}
