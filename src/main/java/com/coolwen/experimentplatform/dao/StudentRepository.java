package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface StudentRepository extends BaseRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

    Student findAllById(int id);



}
