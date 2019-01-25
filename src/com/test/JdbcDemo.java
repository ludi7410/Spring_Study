package com.test;

import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args ) {
        ResultSet resultSet=null;
        Connection conn=null;
        PreparedStatement pstm =null;

        try {
            //1.注册驱动
            Class.forName("oracle.jdbc.OracleDriver");
            //2.获取连接
             conn =DriverManager.getConnection("jdbc:oracle:thin:@172.168.65.220:1521:orcl","gesscitics","gesscitics");
            //3.获取操作数据库的预处理对象
             pstm =conn.prepareStatement("select * from dual");
            //4.执行sql语句并获取返回结果
             resultSet = pstm.executeQuery();
            //5.封装结果集
            while (resultSet.next()){
                System.out.println(resultSet.getString("dummy"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭执行通道
            if(pstm !=null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭连接通道
            try {
                if(conn != null &&(!conn.isClosed())) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

