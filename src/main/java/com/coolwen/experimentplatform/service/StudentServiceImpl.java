package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ClazzRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
import com.coolwen.experimentplatform.specification.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artell
 * @version 2020/5/15 21:31
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;


    @Autowired
    ClazzRepository clazzRepository;

    @Value("${SimplePageBuilder.pageSize}")
    int size;

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

    @Override
    public Page<StudentVo> findStudentsByStuCheckstate(int pageNum) {
        Pageable pageable  = PageRequest.of(pageNum,10);
        return studentRepository.findStudentsByStuCheckstate(pageable);
    }

    @Override
    public StudentVo findStudentsByStuXuehao(String xuehao) {
        return studentRepository.findStudentsByStuXuehao(xuehao);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public StudentVo findStudentVoById(int id) {
        return studentRepository.findStudentsById(id);
    }

    @Override
    public ClassModel findClazzByClassName(String className) {
        return clazzRepository.findClazzByClass_name(className);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Page<Student> findToBeReviewedStudent(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,10);
        return studentRepository.findToBeReviewedStudent(pageable);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }

    @Override
    public Student findStudentByStuXuehao(String xuehao) {
        return studentRepository.findStudentByStuXuehao(xuehao);
    }

    @Override
    public List<Student> findStudentByClassId(int class_id) {
        return studentRepository.findStudentByClassId(class_id);
    }

//    @Override
//    public Page<StuTotalScoreCurrentDTO> listStuTotalScoreCurrentDTO(int pageNum) {
//        Pageable pager = PageRequest.of(pageNum, size);
//        Page<StuTotalScoreCurrentDTO> stuTotalScoreCurrentDTOSPage = studentRepository.findAll(new SimpleSpecificationBuilder<StuTotalScoreCurrentDTO>()
//                .generateSpecification(), pager);
////        userPage.
//        return stuTotalScoreCurrentDTOSPage;
//    }

    @Override
    public Page<StuTotalScoreCurrentDTO> listStuTotalScoreCurrentDTO(int pageNum) {
        Pageable pager = PageRequest.of(pageNum, size);
        return studentRepository.listStuTotalScoreCurrentDTO(pager);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

}
