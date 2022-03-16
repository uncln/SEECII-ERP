package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.CategoryVO;

public interface CategoryService {

    /**
     * 创建分类
     * @param parentId 父节点Id
     * @param name 分类名
     * @return 邀请码，题目等内容
     */
    CategoryVO createExam(Integer parentId, String name);
}
