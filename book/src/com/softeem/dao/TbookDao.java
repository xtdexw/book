package com.softeem.dao;

import com.softeem.bean.Tbook;
import com.softeem.utils.BaseInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface TbookDao extends BaseInterface<Tbook> {
    /**
     *根据关键字查询用户信息
     *@param keyword 用户名
     *@return 返回一组数据
     */
    public List<Tbook> queryBookByKeyword(String keyword) throws SQLException;

    public List<Tbook> queryForPageItems(int begin, int pageSize) throws SQLException;

    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException;

    public List<Tbook> queryForPageItems(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException;

}
