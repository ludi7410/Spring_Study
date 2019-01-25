package com.service.impl;

import com.dao.ICustomDao;
import com.dao.impl.ICustomDaoImpl;
import com.factory.BeanFactory;
import com.service.ICustomService;

/**
 * 客户业务层实现类
 */
public class ICustomServiceImpl implements ICustomService {
  // private ICustomDao customerDao =new ICustomDaoImpl();
    @Override
    public void saveCustomer() {
        System.out.println("业务层实现类正在执行业务逻辑!");
        //ICustomDao customerDao= BeanFactory.getCustomerDao();
        ICustomDao customerDao= (ICustomDao)BeanFactory.getBean("CUSTOMDAOIMPL");
        customerDao.saveCustomer();//调用持久层保存到数据库
    }
}
