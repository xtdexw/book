package com.softeem.test;

import com.softeem.bean.Tbook;
import com.softeem.dao.TbookDao;
import com.softeem.dao.impl.TbookDaoimpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class TbookDaoimplTest {

    private TbookDao tbookDao = new TbookDaoimpl();

    @Test
    public void queryForPageTotalCount() throws SQLException {
        Integer i = tbookDao.queryForPageTotalCount(null, "周杰伦", null, null);
        System.out.println(i);
    }

    @Test
    public void queryForPageItems() throws SQLException {
        List<Tbook> tbookList = tbookDao.queryForPageItems(1, 4,null , null, new BigDecimal(1), new BigDecimal(1000));
        for (Tbook tbook : tbookList) {
            System.out.println("tbook = " + tbook);
        }
    }
}