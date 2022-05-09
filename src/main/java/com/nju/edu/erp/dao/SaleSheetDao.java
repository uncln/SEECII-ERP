package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.SaleSheetContentPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleSheetDao {

    /**
     * 获取最近一条销售单
     * @return
     */
    SaleSheetPO getLatestSheet();

    /**
     * 存入一条销售单记录
     * @param toSave 一条销售单记录
     * @return 影响的行数
     */
    int saveSheet(SaleSheetPO toSave);

    /**
     * 把销售单上的具体内容存入数据库
     * @param saleSheetContent 入销售单上的具体内容
     */
    int saveBatchSheetContent(List<SaleSheetContentPO> saleSheetContent);

    /**
     * 查找所有销售单
     */
    List<SaleSheetPO> findAllSheet();

    /**
     * 查找指定状态的销售单
     * @param state
     */
    List<SaleSheetPO> findSheetByState(SaleSheetState state);

    /**
     * 查找指定id的销售单
     * @param id
     * @return
     */
    SaleSheetPO findSheetById(String id);

    /**
     * 查找指定销售单下具体的商品内容
     * @param sheetId
     */
    List<SaleSheetContentPO> findContentBySheetId(String sheetId);

    /**
     * 更新指定销售单的状态
     * @param sheetId
     * @param state
     * @return
     */
    int updateSheetState(String sheetId, SaleSheetState state);

    /**
     * 根据当前状态更新销售单状态
     * @param sheetId
     * @param prev
     * @param state
     * @return
     */
    int updateSheetStateOnPrev(String sheetId, SaleSheetState prev, SaleSheetState state);
}
