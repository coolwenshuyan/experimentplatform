package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ClazzRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.*;
import com.coolwen.experimentplatform.model.Student;
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
    public StudentListDTO findStudentDTOById(int id) {
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
    public Student findclassStudentByStuXuehao(String xuehao) {
        return studentRepository.findclassStudentByStuXuehao(xuehao);
    }

    @Override
    public List<Student> findStudentByClassId(int class_id) {
        return studentRepository.findStudentByClassId(class_id);
    }

    @Override
    public Page<Student> pageStudentByClassId(int page, int classId) {
        Pageable pager = PageRequest.of(page, size);
        Page<Student> studentsPage = studentRepository.findAll(new SimpleSpecificationBuilder<Student>(
                "classId", "=", classId)
                .generateSpecification(), pager);
        return studentsPage;
    }



//    @Override
//    public Page<Student> pageStudentByClassId(int class_id) {
//        return studentRepository.pageStudentByClassId(class_id);
//    }

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

//    @Override
//    public Page<Student> findStudentPageAndXuehaoAndClass(int page, String select_orderId, int classId) {
//        Pageable pager = PageRequest.of(page, size);
//        Page<Student> studentsPage = studentRepository.findAll(new SimpleSpecificationBuilder<Student>(
//                "stuXuehao", ":", select_orderId).add("classId",":",classId)
////                .add(SpecificationOperator.Join.and, key, operator, value);
//                .generateSpecification(), pager);
//        return studentsPage;
//    }

    @Override
    public Page<Student> findStudentPageAndXuehao(int page, String select_orderId) {
        Pageable pager = PageRequest.of(page, size);
        Page<Student> studentsPage = studentRepository.findAll(new SimpleSpecificationBuilder<Student>(
                "stuXuehao", ":", select_orderId)
                .generateSpecification(), pager);
        return studentsPage;
    }

    @Override
    public Page<StudentLastTestScoreDTO> listStudentLastTestAnswerDTO(int pageNum) {
        Pageable pager = PageRequest.of(pageNum, size);
        return studentRepository.listStudentLastTestScoreDTO(pager);
    }

    @Override
    public Page<StudentLastTestScoreDTO> listStudentLastTestScoreDTOBYClassID(int pageNum, int classId) {
        Pageable pager = PageRequest.of(pageNum, size);
        return studentRepository.listStudentLastTestScoreDTOByClassID(classId,pager);
    }


}
