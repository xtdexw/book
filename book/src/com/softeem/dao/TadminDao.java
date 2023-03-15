package com.softeem.dao;

import com.softeem.bean.Tadmin;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;

public interface TadminDao extends BaseInterface<Tadmin> {
    /**
     *根据 用户名和密码查询用户信息
     *@param tadmin
     *@return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public Tadmin queryUserByUsernameAndPassword(Tadmin tadmin) throws SQLException;
}
