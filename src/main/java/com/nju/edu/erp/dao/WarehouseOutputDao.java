package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehouseOutputPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WarehouseOutputDao {
    WarehouseOutputPO getLatest();

    void save(WarehouseOutputPO toSave);
}
