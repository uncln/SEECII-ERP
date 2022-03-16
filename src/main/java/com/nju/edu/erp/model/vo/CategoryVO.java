package com.nju.edu.erp.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 父分类ID
     */
    private Integer parentId;

    /**
     * 是否为叶节点
     */
    private boolean isLeaf;

    /**
     * 商品数量
     */
    private Integer itemCount;

    /**
     * 下一个商品index
     */
    private Integer itemIndex;
}