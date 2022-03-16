package com.nju.edu.erp.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class WarehouseOutputListPO {
    /**
     * 出库商品列表id
     */
    private Integer id;
    /**
     * 商品id
     */
    private String pid;
    /**
     * 出库单编号
     */
    private String woId;
    /**
     * 批次
     */
    private Integer batchId;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private BigDecimal purchasePrice;
    /**
     * 备注
     */
    private String remark;
}
