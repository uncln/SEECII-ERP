package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseInputListPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehouseInputListDao {
    void saveBatch(List<WarehouseInputListPO> warehouseInputListPOList);
}
