package com.softeem.utils;

import org.apache.commons.dbutils.*;

/**
 * BaseDao的目的就是去实现各种Dao类
 */
public abstract class BaseDao {
    public QueryRunner queryRunner ;
    public int pageSize = 4 ;
    public BaseDao(){
        queryRunner = new QueryRunner(MyDataSource.getDataSource());
    }

    public RowProcessor hump(){
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        return processor;
    }
}
