package com.softeem.dao.impl;


import com.softeem.bean.Tbook;
import com.softeem.dao.TbookDao;
import com.softeem.utils.BaseDao;
import com.softeem.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TbookDaoimpl extends BaseDao implements TbookDao {

    @Override
    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
       StringBuffer sql = new StringBuffer("select count(*) from t_book where 1 = 1 ");
        ArrayList list = new ArrayList();
        if( name != null && !"".equals(name) ){
            sql.append(" and name like ? ");
            list.add("%"+name+"%");
        }
        if( author != null && !"".equals(author) ){
            sql.append(" and author like ? ");
            list.add("%"+author+"%");
        }
        if( (min != null && min.signum() == 1) && (max != null && max.signum() == 1) ){
            //min值 大于 max值
            if(min.compareTo(max)==1){ //进行两值交换
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        }else if( min != null && min.signum() == 1){
            sql.append(" and price >= ? ");
            list.add(min);
        }else if( max != null && max.signum() == 1){
            sql.append(" and price <= ? ");
            list.add(max);
        }
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query(sql.toString(),handler,list.toArray());
        return query.intValue();
    }

    @Override
    public List<Tbook> queryForPageItems(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuffer sql = new StringBuffer("select * from t_book where 1 = 1 ");
        ArrayList list = new ArrayList();
        if( name != null && !"".equals(name) ){
            sql.append(" and name like ? ");
            list.add("%"+name+"%");
        }
        if( author != null && !"".equals(author) ){
            sql.append(" and author like ? ");
            list.add("%"+author+"%");
        }
        if( (min != null && min.signum() == 1) && (max != null && max.signum() == 1) ){
            //min值 大于 max值
            if(min.compareTo(max)==1){
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        }else if( min != null && min.signum() == 1){
            sql.append(" and price >= ? ");
            list.add(min);
        }else if( max != null && max.signum() == 1){
            sql.append(" and price <= ? ");
            list.add(max);
        }
        String end = " order by id desc limit ?,? ";
        sql.append(end);

        list.add(begin);
        list.add(pageSize);
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class,hump());
        List<Tbook> query = queryRunner.query(sql.toString(), handler, list.toArray());
        return query;
    }

    @Override
    public List<Tbook> queryBookByKeyword(String keyword) throws SQLException {
        String sql = "select * from t_book where name like ?";
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class,hump());
        List<Tbook> query = queryRunner.query(sql, handler, "%"+keyword+"%");
        return query;
    }

    @Override
    public List<Tbook> queryForPageItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from t_book order by id desc limit ?,?";
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class,hump());
        List<Tbook> query = queryRunner.query(sql, handler, begin, pageSize);
        return query;
    }

    @Override
    public List<Tbook> findAll() throws SQLException {
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class,hump());
        List<Tbook> query = queryRunner.query("select * from t_book order by id desc", handler);
        return query;
    }

    @Override
    public void save(Tbook tbook) throws SQLException {
        queryRunner.update("insert into t_book values(null,?,?,?,?,?,?)",tbook.getName(),
                tbook.getPrice(),tbook.getAuthor(),tbook.getSales(),tbook.getStock(),tbook.getImgPath());
    }

    @Override
    public void updateById(Tbook tbook) throws SQLException {
//        Connection conn = JdbcUtils.getConnection();
        String sql = "update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ? ";
        queryRunner.update(sql,tbook.getName(),tbook.getPrice(),tbook.getAuthor(),tbook.getSales(),tbook.getStock(),tbook.getImgPath(),tbook.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_book where id = ?",id);
    }

    @Override
    public Tbook findById(Integer id) throws SQLException {
        BeanHandler<Tbook> handler = new BeanHandler<>(Tbook.class,hump());
        Tbook query = queryRunner.query("select * from t_book where id = ?", handler, id);
        return query;
    }

    @Override
    public List<Tbook> page(Integer pageNumber) throws SQLException {
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class,hump());
        List<Tbook> query = queryRunner.query("select * from t_book order by id desc limit ?,?", handler, (pageNumber - 1) * pageSize, pageSize);
        return query;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query("select count(*) from t_book", handler);
        return query.intValue();
    }
}
