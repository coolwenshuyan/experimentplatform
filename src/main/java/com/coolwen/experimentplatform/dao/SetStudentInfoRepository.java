package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SetStudentInfoRepository extends PagingAndSortingRepository <Student,Integer>{
    @Query(value = "select * from t_student where id=?",nativeQuery = true)
    Student findStudentById(int id);
}
