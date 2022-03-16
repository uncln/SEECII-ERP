package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseOutputListPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehouseOutputListDao {
    void saveBatch(List<WarehouseOutputListPO> warehouseOutputListPOList);
}
