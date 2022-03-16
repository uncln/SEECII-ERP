package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.CreateProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductDao {

    int createProduct(CreateProductVO createProductVO);

    int updateById(ProductPO productPO);

    ProductPO findById(String id);
}
