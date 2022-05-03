package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    /**
     * 根据id更新客户信息
     *
     * @param customerVO 客户信息
     */
    @Override
    public void updateCustomer(CustomerVO customerVO) {
        // TODO

    }

    /**
     * 根据type查找对应类型的客户
     *
     * @param type 客户类型
     * @return 客户列表
     */
    @Override
    public List<CustomerPO> getCustomersByType(CustomerType type) {
        // TODO
        return null;
    }
}
