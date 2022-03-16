package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseInputPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WarehouseInputDao {
    WarehouseInputPO getLatest();

    int save(WarehouseInputPO toSave);
}
