package com.coolwen.experimentplatform.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.dao
 * @ClassName: AdminDaoTest
 * @Author: Txc
 * @Description:
 * @Date: 2020/5/15 0015 15:01
 * @Version: 1.0
 */
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;


    @Test
    void findTeacherById() {
        System.out.println(adminDao.findTeacherById(1).getUname());
    }
}