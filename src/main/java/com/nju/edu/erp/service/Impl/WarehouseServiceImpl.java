package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.warehouse.*;
import com.nju.edu.erp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final ProductDao productDao;
    private final WarehouseDao warehouseDao;
    private final WarehouseInputDao warehouseInputDao;
    private final WarehouseInputListDao warehouseInputListDao;
    private final WarehouseOutputDao warehouseOutputDao;
    private final WarehouseOutputListDao warehouseOutputListDao;

    @Autowired
    public WarehouseServiceImpl(ProductDao productDao, WarehouseDao warehouseDao, WarehouseInputDao warehouseInputDao, WarehouseInputListDao warehouseInputListDao, WarehouseOutputDao warehouseOutputDao, WarehouseOutputListDao warehouseOutputListDao) {
        this.productDao = productDao;
        this.warehouseDao = warehouseDao;
        this.warehouseInputDao = warehouseInputDao;
        this.warehouseInputListDao = warehouseInputListDao;
        this.warehouseOutputDao = warehouseOutputDao;
        this.warehouseOutputListDao = warehouseOutputListDao;
    }


    @Override
    @Transactional
    public void productWarehousing(WarehouseInputFormVO warehouseInputFormVO) {
        /**
         * 商品入库
         * 1. 查看上一次入库单
         * 2. 根据上一次入库单来创建新入库单(单号/批次号/...)
         * 3. 更新"商品表", 插入"入库单表", 插入"入库单物品列表"表, 插入"库存表"
         */
        WarehouseInputPO warehouseInputPO =  warehouseInputDao.getLatest();
        if(warehouseInputPO == null) warehouseInputPO = WarehouseInputPO.builder().batchId(-1).build();
        WarehouseInputPO toSave = new WarehouseInputPO();
        toSave.setId(generateWarehouseInputId(warehouseInputPO.getId(), warehouseInputPO.getBatchId()));
        toSave.setBatchId(generateBatchId(warehouseInputPO.getBatchId()));
        toSave.setOperator(warehouseInputFormVO.getOperator());
        toSave.setUpdateTime(new Date());

        List<WarehouseInputListPO> warehouseInputListPOList = new ArrayList<>();
        List<WarehousePO> warehousePOList = new ArrayList<>();
        warehouseInputFormVO.getList().forEach(item -> {
            ProductPO productPO = productDao.findById(item.getPid());
            productPO.setQuantity(productPO.getQuantity()+item.getQuantity());
            productPO.setRecentPp(item.getPurchasePrice());
            productDao.updateById(productPO);

            BigDecimal purchasePrice = item.getPurchasePrice();
            if(purchasePrice == null) {
                purchasePrice = productPO.getPurchasePrice();
            }
            WarehouseInputListPO  warehouseInputListPO = WarehouseInputListPO.builder()
                    .wiId(toSave.getId())
                    .pid(item.getPid())
                    .quantity(item.getQuantity())
                    .purchasePrice(purchasePrice)
                    .productionDate(item.getProductionDate())
                    .remark(item.getRemark()).build();
            warehouseInputListPOList.add(warehouseInputListPO);
            WarehousePO warehousePO = WarehousePO.builder()
                    .pid(item.getPid())
                    .quantity(item.getQuantity())
                    .purchasePrice(purchasePrice)
                    .batchId(toSave.getBatchId())
                    .productionDate(item.getProductionDate()).build();
            warehousePOList.add(warehousePO);
        } );

        warehouseInputDao.save(toSave);
        warehouseInputListDao.saveBatch(warehouseInputListPOList);
        warehouseDao.saveBatch(warehousePOList);
    }

    @Override
    @Transactional
    public void productOutOfWarehouse(WarehouseOutputFormVO warehouseOutputFormVO) {
        /**
         * 商品出库
         * 1. 查到上一次出库单的ID
         * 2. 根据上一次出库单来创建新出库单
         * 3. 更新"ware..output" "ware..list_output" "warehouse" 和 "product"表
         * 逻辑跟创建入库单相似,
         *      区别有：     1. 出库单的单号需要换种方式计算
         *                 2. 批次是从前端传进来的
         *                 3. 对于warehouse表采取批量更新而不是批量新增操作
         */
        WarehouseOutputPO warehouseOutputPO = warehouseOutputDao.getLatest();
        WarehouseOutputPO toSave = new WarehouseOutputPO();
        toSave.setId(generateWarehouseOutputId(warehouseOutputPO == null ? null : warehouseOutputPO.getId()));
        toSave.setOperator(warehouseOutputFormVO.getOperator());
        toSave.setUpdateTime(new Date());

        List<WarehouseOutputListPO> warehouseOutputListPOList = new ArrayList<>();
        warehouseOutputFormVO.getList().forEach(item -> {
            ProductPO productPO = productDao.findById(item.getPid());
            productPO.setQuantity(productPO.getQuantity()-item.getQuantity());
            productDao.updateById(productPO);

            WarehouseOutputListPO  warehouseOutputListPO = WarehouseOutputListPO.builder()
                    .woId(toSave.getId())
                    .pid(item.getPid())
                    .quantity(item.getQuantity())
                    .purchasePrice(item.getPurchasePrice())
                    .batchId(item.getBatchId())
                    .remark(item.getRemark()).build();
            warehouseOutputListPOList.add(warehouseOutputListPO);
            WarehousePO warehousePO = WarehousePO.builder()
                    .pid(item.getPid())
                    .batchId(item.getBatchId())
                    .quantity(item.getQuantity())
                    .build();
            warehouseDao.deductQuantity(warehousePO);
        } );

        warehouseOutputDao.save(toSave);
        warehouseOutputListDao.saveBatch(warehouseOutputListPOList);
    }

    /**
     * 获取出库单新单号
     * @param id 上一次的出库单单号
     * @return 新的出库单单号
     */
    private String generateWarehouseOutputId(String id) { // "CKD-20220216-00000"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        if(id == null) {
            return "CKD-" + today + "-" + String.format("%05d", 0);
        }
        String lastDate = id.split("-")[1];
        if(lastDate.equals(today)) {
            String prevNum = id.split("-")[2];
            return "CKD-" + today + "-" + String.format("%05d", Integer.parseInt(prevNum) + 1);
        } else {
            return "CKD-" + today + "-" + String.format("%05d", 0);
        }
    }

    @Override
    public List<WarehouseOneProductInfoVO> getWareProductInfo(GetWareProductInfoParamsVO params) {
        /**
         * 这是商品出库的前驱步骤 ——
         * 先查对应商品的批次, 把不同批次的商品按照入库的时间顺序取出
         * [比如有三批相同商品,分别进了100个,现在出库需要150个,那现在取第一批的100个和第二批的50个],
         * 然后返回给前端。
         * 前端可以继续选择不同的商品出库, 最终各种批次的各种商品组织成list作为出库单的参数
         */
        int quantity = params.getQuantity();
        List<WarehousePO> warehousePOS = warehouseDao.findAllNotZeroByPidSortedByBatchId(params.getPid());
        List<WarehouseOneProductInfoVO> res = new ArrayList<>();
        for (int i = 0; i < warehousePOS.size() && quantity > 0; i++) {
            WarehousePO warehousePO = warehousePOS.get(i);
            WarehouseOneProductInfoVO resItem = WarehouseOneProductInfoVO.builder()
                    .productId(warehousePO.getPid())
                    .batchId(warehousePO.getBatchId())
                    .purchasePrice(warehousePO.getPurchasePrice())
                    .totalQuantity(warehousePO.getQuantity())
                    .remark(params.getRemark())
                    .build();
            if (warehousePO.getQuantity() <= quantity) {
                resItem.setSelectedQuantity(warehousePO.getQuantity());
                resItem.setSumPrice(warehousePO.getPurchasePrice().multiply(BigDecimal.valueOf(warehousePO.getQuantity())));
                quantity -= warehousePO.getQuantity();
            } else {
                resItem.setSelectedQuantity(quantity);
                resItem.setSumPrice(warehousePO.getPurchasePrice().multiply(BigDecimal.valueOf(quantity)));
                quantity = 0;
            }
            res.add(resItem);
        }
        return res;
    }
    /**
     * 获取新批次
     * @param batchId 批次
     * @return 新批次
     */
    private Integer generateBatchId(Integer batchId) {
        return batchId + 1;
    }

    /**
     * 获取新入库单单号
     * @param id 入库单单号
     * @return 新入库单单号
     */
    private String generateWarehouseInputId(String id, Integer batchId) { // "RKD-20220216-00000"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        if(batchId == -1) {
            return "RKD-" + today + "-" + String.format("%05d", 0);
        }
        String lastDate = id.split("-")[1];
        if(lastDate.equals(today)) {
            return "RKD-" + today + "-" + String.format("%05d", batchId + 1);
        } else {
            return "RKD-" + today + "-" + String.format("%05d", 0);
        }
    }
}
