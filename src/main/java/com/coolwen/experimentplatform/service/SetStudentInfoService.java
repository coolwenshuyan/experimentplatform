package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ClassDao;
import com.coolwen.experimentplatform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

public interface SetStudentInfoService {

    Student findById(int id);

    void add(Student student);

    String findByClassName(int id);

}
