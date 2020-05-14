package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Admin;


import java.util.List;

public interface AdminService {

    //查找
    Admin findById(int id);

    // 增加
    void add(Admin admin);

    //删除
    void delete(int id);

}
