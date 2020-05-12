package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Teacher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher,Integer> {
    @Query(value = "select * from t_teacher where id = ?",nativeQuery = true)
    Teacher findTeacherById(int id);

}
