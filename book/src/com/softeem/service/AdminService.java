package com.softeem.service;
import com.softeem.bean.Tadmin;

import java.sql.SQLException;

public interface AdminService {
    /**
     *登录
     *@param tadmin
     *@return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public Tadmin login(Tadmin tadmin) throws SQLException;
}
