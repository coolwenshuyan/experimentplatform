package com.coolwen.experimentplatform.service;
import com.coolwen.experimentplatform.dao.AdminDao;
import com.coolwen.experimentplatform.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;


    @Override
    public void add(Admin admin) {
        adminDao.save(admin);
    }


    @Override
    public Admin findById(int id) {
        Admin admin = adminDao.findTeacherById(id);
        return admin;
    }

    @Override
    public Admin findByUname(String uName) {
        return adminDao.findByUname(uName);
    }


    @Override
    public void delete(int id) {
        adminDao.deleteById(id);
    }
}


