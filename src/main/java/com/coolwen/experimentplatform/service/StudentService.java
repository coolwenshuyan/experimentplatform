package com.coolwen.experimentplatform.service;


import com.coolwen.experimentplatform.model.Student;
import org.springframework.data.domain.Page;

/**
 * @author Artell
 * @version 2020/5/15 21:30
 */


public interface StudentService {
    Page<Student> findAll(int pageNum);
    public Student findByUname(String uName);
    public Student addStudent(Student student);
}
