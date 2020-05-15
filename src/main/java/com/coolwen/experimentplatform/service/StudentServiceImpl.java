package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Artell
 * @version 2020/5/15 21:31
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(int pageNum) {
        Pageable pageable  = PageRequest.of(pageNum,10);
        return studentRepository.findAll(pageable);
    }


    @Override
    public Student findByUname(String uName) {
        return studentRepository.findAllByStuUname(uName);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

}
