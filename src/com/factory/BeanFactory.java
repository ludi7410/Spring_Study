package com.factory;

import com.dao.ICustomDao;
import com.service.ICustomService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 一个工厂类
 */
public class BeanFactory {
    private static  Properties pros=new Properties();
//    static { //静态块加载配置文件
//        try {
//            InputStream in =  BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");//类路径目录下
//       //  InputStream in = new FileInputStream("src/bean.properties");//此方法在web项目中不可用，发布时src目录就没了
//
//            pros.load(in);
//            System.out.println(pros.get("CUSTOMDAOIMPL"));
//        } catch (Exception e) {
//            System.out.println("读取配置文件失败！");
//            e.printStackTrace();
//        }
//    }
//------------------------------------------------------------------------

    /**
     * 此方法只能读取properties文件，别的文件读不了
     * 只可用于读取，不能用于写入
     * 只能读取类路径下的，不在类路径下读取不到
     * 方法参数的写法按照包名，类名方法，不可写后缀名
     */
    private static ResourceBundle bundle =ResourceBundle.getBundle("bean");

    /**
     * 定义一个容器，存放对象
     */
    private static Map<String,Object> beans =new HashMap<String, Object>();

    //使用静态代码块，初始化map赋值
    static {
        try {
            //1.读取配置文件中key的部分
            Enumeration<String> keys = bundle.getKeys();
            //2.遍历keys
            while(keys.hasMoreElements()){
                //3.取出一个key
                String key = keys.nextElement();
                //4.根据key获取beanPath
                String beanPath = bundle.getString(key);
                //5.根据beanPath反射创建类
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key,value);
            }
        } catch (Exception e) {
            throw  new ExceptionInInitializerError("容器创建失败，程序停止执行！");
        }
    }

    public static Object getBean(String beanName){
        try {
            System.out.println("工厂在实例化Bean实现类");
         //   String beanPath=bundle.getString(beanName);
          //  return  Class.forName(beanPath).newInstance();
            return  beans.get(beanName);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    public static ICustomService getCustomerService(){
        try {
            System.out.println("工厂在实例化Bean业务层实现类");
            return (ICustomService) Class.forName("com.service.impl.ICustomServiceImpl").newInstance();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    public static ICustomDao getCustomerDao() {
        try {
            System.out.println("工厂在实例化Bean持久层实现类");
            return (ICustomDao) Class.forName("com.dao.impl.ICustomDaoImpl").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}