package com.dao.impl;

import com.dao.ICustomDao;

/**
 * 持久层 保存客户的实现类   与数据库连接
 */
public class ICustomDaoImpl implements ICustomDao {
    @Override
    public void saveCustomer() {

        System.out.println("持久层保存用户到了数据库！");
    }
}
