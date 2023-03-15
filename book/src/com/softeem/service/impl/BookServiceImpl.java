package com.softeem.service.impl;

import com.softeem.bean.Tbook;
import com.softeem.dao.TbookDao;
import com.softeem.dao.impl.TbookDaoimpl;
import com.softeem.service.BookService;
import com.softeem.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private TbookDao tbookDao = new TbookDaoimpl();


    @Override
    public List<Tbook> queryBookByKeyword(String keyword) throws SQLException {
        return null;
    }

    @Override
    public void addBook(Tbook tbook) throws SQLException {
        tbookDao.save(tbook);
    }

    @Override
    public void deletebookById(Integer id) throws SQLException {
        tbookDao.deleteById(id);
    }

    @Override
    public void updateBook(Tbook tbook) throws SQLException {
        tbookDao.updateById(tbook);
    }

    @Override
    public Tbook queryBookById(Integer id) throws SQLException {
        return tbookDao.findById(id);
    }

    @Override
    public List<Tbook> queryBooks() throws SQLException {
        return tbookDao.findAll();
    }

    @Override
    public Page<Tbook> page(int begin, int pageSize) throws SQLException {
        Page<Tbook> page = new Page<>();
        Integer totalCount = tbookDao.pageRecord();//查询总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize-1) / pageSize);//求出总页数
        page.setPageNo(begin);//设置当前页
        List<Tbook> tbookList = tbookDao.queryForPageItems((page.getPageNo()-1)*pageSize, pageSize);
        page.setItems(tbookList);
        return page;
    }

    @Override
    public Page<Tbook> page(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        Page<Tbook> page = new Page<>();
        Integer totalCount = tbookDao.queryForPageTotalCount(name,author,min,max);//查询总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize-1) / pageSize);//求出总页数
        page.setPageNo(begin);//设置当前页
        List<Tbook> tbookList = tbookDao.queryForPageItems((page.getPageNo()-1)*pageSize, pageSize,name,author,min,max);
        page.setItems(tbookList);
        return page;
    }

}
