package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.model.DTO.StudentLastTestScoreDTO;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
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

    Student findclassStudentByStuXuehao(String xuehao);

    List<Student> findStudentByClassId(int class_id);

    Page<Student> pageStudentByClassId(int class_id,int classid);

    Page<StuTotalScoreCurrentDTO> listStuTotalScoreCurrentDTO(int pageNum);

    List<Student> findAll();

    public Page<Student> findStudentPageAndXuehao(int page, String select_orderId);

    Page<StudentLastTestScoreDTO> listStudentLastTestAnswerDTO(int pageNum);

    Page<StudentLastTestScoreDTO> listStudentLastTestScoreDTOBYClassID(int pageNum,int classId);
}
