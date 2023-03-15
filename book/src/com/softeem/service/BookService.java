package com.softeem.service;


import com.softeem.bean.Tbook;
import com.softeem.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    /**
     *查询信息
     *@param keyword
     */
    public List<Tbook> queryBookByKeyword(String keyword) throws SQLException;

    public void addBook(Tbook tbook) throws SQLException;

    public void deletebookById(Integer id) throws SQLException;

    public void updateBook(Tbook tbook) throws SQLException;

    public Tbook queryBookById(Integer id) throws SQLException;

    public List<Tbook> queryBooks() throws SQLException;

    public Page<Tbook> page(int begin, int pageSize) throws SQLException;

    public Page<Tbook> page(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException;
}
