package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminDao extends PagingAndSortingRepository<Admin, Integer> {
    @Query(value = "select * from t_admin where id=?",nativeQuery = true)
    Admin findTeacherById(int id);

    Admin findByUname(String name);
}
