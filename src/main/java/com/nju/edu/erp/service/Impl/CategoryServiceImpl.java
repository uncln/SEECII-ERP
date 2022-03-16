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
    public CategoryVO createExam(Integer parentId, String name) {

        CategoryPO categoryPO = new CategoryPO(null, name, parentId, true, 0, 1);

        int ans = categoryDao.createCategory(categoryPO);

        CategoryVO categoryVO = new CategoryVO();

        return categoryVO;
    }
}
