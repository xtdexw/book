package com.softeem.service.impl;

import com.softeem.bean.Tadmin;
import com.softeem.bean.User;
import com.softeem.dao.TadminDao;
import com.softeem.dao.impl.TadminDaoImpl;
import com.softeem.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {

    private TadminDao tadminDao = new TadminDaoImpl();


    @Override
    public Tadmin login(Tadmin tadmin) throws SQLException {
        return tadminDao.queryUserByUsernameAndPassword(tadmin);
    }


}
