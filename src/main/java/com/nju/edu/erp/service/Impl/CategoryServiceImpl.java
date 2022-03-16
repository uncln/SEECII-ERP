package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CategoryDao;
import com.nju.edu.erp.model.po.CategoryPO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryVO createCategory(Integer parentId, String name) {

        // 获取父节点，判断是否能够进行插入
        // CategoryPO fatherPO = categoryDao.findByCategoryId(parentId);

        //if (fatherPO.getItemCount() > 0 )

        CategoryPO categoryPO = new CategoryPO(null, name, parentId, true, 0, 1);

        int ans = categoryDao.createCategory(categoryPO);

        CategoryVO categoryVO = new CategoryVO();

        return categoryVO;
    }



}
