package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.vo.StudentVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author CoolWen
 * @version 2020-05-16 13:48
 */
public interface StudentService {
    Page<Student> findAll(int pageNum);

    public Student findByUname(String uName);

    public Student addStudent(Student student);

    Page<StudentVo> findStudentsByStuCheckstate(int pageNum);

    StudentVo findStudentsByStuXuehao(String xuehao);

    void deleteStudentById(int id);

    Student findStudentById(int id);

    StudentVo findStudentVoById(int id);

    ClassModel findClazzByClassName(String className);

    void saveStudent(Student student);

    Page<Student> findToBeReviewedStudent(int pageNum);

    void deleteStudent(int id);

    Student findStudentByStuXuehao(String xuehao);

    List<Student> findStudentByClassId(int class_id);


}
