package com.ui;

import com.factory.BeanFactory;
import com.service.ICustomService;
import com.service.impl.ICustomServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args){
      //  ICustomService customService =new ICustomServiceImpl();
       // ICustomService customService= BeanFactory.getCustomerService();
        ICustomService customService= (ICustomService)BeanFactory.getBean("CUSTMOSERVICE");

        customService.saveCustomer();
    }
}
