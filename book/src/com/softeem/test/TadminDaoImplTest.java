package com.softeem.test;

import com.softeem.bean.Tadmin;
import com.softeem.dao.TadminDao;
import com.softeem.dao.impl.TadminDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TadminDaoImplTest {

    private TadminDao tadminDao = new TadminDaoImpl();

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws SQLException {
        Tadmin tadmin = new Tadmin(null,"admin","admin");
        tadminDao.save(tadmin);
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() throws SQLException {
        Tadmin byId = tadminDao.findById(1);
        System.out.println("byId = " + byId);
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }

    @Test
    public void queryUserByUsernameAndPassword() throws SQLException {
    }
}