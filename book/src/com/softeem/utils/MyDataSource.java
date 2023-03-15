package com.softeem.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

//类似单例模式
public class MyDataSource {
    public static DataSource dataSource = null;

    private MyDataSource(){

    }
   public static DataSource getDataSource(){
        if(dataSource == null) {
            try {
                Properties pro = new Properties();
                pro.load(MyDataSource.class.getClassLoader().getResourceAsStream("jdbc.properties"));
                //创建数据源对象
                dataSource = DruidDataSourceFactory.createDataSource(pro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}
