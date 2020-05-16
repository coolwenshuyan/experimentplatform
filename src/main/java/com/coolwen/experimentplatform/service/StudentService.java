package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Student;
import org.springframework.data.domain.Page;

/**
 * @author CoolWen
 * @version 2020-05-16 13:48
 */
public interface StudentService {
    Page<Student> findAll(int pageNum);

    public Student findByUname(String uName);

    public Student addStudent(Student student);


}
