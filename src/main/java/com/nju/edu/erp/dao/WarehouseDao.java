package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.WarehousePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehouseDao {
    void saveBatch(List<WarehousePO> warehousePOList);

    void deductQuantity(WarehousePO warehousePO);

    List<WarehousePO> findAllNotZeroByPidSortedByBatchId(String pid);


    WarehousePO findOneByPidAndBatchId(String pid, Integer batchId);

    /**
     * 按照商品id获取现存商品（存量>0）并按价格排序
     * @param pid
     * @return
     */
    List<WarehousePO> findByPidOrderByPurchasePricePos(String pid);
}
