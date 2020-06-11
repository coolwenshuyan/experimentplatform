package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.*;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.specification.SimpleSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface StudentRepository extends BaseRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

    Student findAllById(int id);

    Student findByStuMobile(String tel);

    Student findByStuXuehao(String xuehao);

    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO" +
            "(st.id, st.stuName, st.classId, expm.m_name, khms.mTestScore, khms.mTeststate,khm.m_id)" +
            "from Student st ,KaoHeModelScore khms ,ExpModel expm ,KaoheModel khm " +
            "where st.id=khms.stuId and khms.tKaohemodleId=khm.id and khm.m_id = expm.m_id ")
    public List<StudentTestScoreDTO> listStudentMTestAnswerDTO();


    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO " +
            "(st.id, st.stuName, st.classId, expm.m_name, khms.mReportScore, khms.mReportstate,khm.m_id) " +
            "from Student st ,KaoHeModelScore khms ,ExpModel expm ,KaoheModel khm " +
            "where st.id=khms.stuId and khms.tKaohemodleId=khm.id and khm.m_id = expm.m_id ")
    public List<StudentTestScoreDTO> listStudentMReportAnswerDTO();

    //只处理当期班级的学生成绩
    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentLastTestScoreDTO " +
            "(st.stuXuehao, st.stuName, clas.className,tsc.totalScore) " +
            "from Student st left join TotalScoreCurrent tsc on st.id = tsc.stuId " +
            "left join ClassModel clas on clas.classId = st.classId where clas.classIscurrent = false ")
    public Page<StudentLastTestScoreDTO> listStudentLastTestScoreDTO(Pageable page);

    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentLastTestScoreDTO " +
            "(st.stuXuehao, st.stuName, clas.className,tsc.totalScore) " +
            "from Student st left join TotalScoreCurrent tsc on st.id = tsc.stuId " +
            "left join ClassModel clas on clas.classId = st.classId " +
            "where st.classId = ?1")
    public Page<StudentLastTestScoreDTO> listStudentLastTestScoreDTOByClassID(int classId,Pageable page);


//    @Query("select new com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO " +
//            "(st.stuXuehao,st.stuName,cla.className,tsc.mTotalScore,tsc.testScore,tsc.totalScore) " +
//            "from Student st left join TotalScoreCurrent tsc on st.id = tsc.stuId " +
//            "left join ClassModel cla on st.classId=cla.classId")
//    List<StuTotalScoreCurrentDTO> listStuTotalScoreCurrentDTO();

    @Query("select new com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO " +
            "(st.stuXuehao,st.stuName,cla.className,tsc.mTotalScore,tsc.testScore,tsc.totalScore) " +
            "from Student st left join TotalScoreCurrent tsc on st.id = tsc.stuId " +
            "left join ClassModel cla on st.classId=cla.classId where cla.classIscurrent = false ")
    Page<StuTotalScoreCurrentDTO> listStuTotalScoreCurrentDTO(Pageable page);


    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentVo(s.id,s.stuUname,s.stuPassword,s.stuName,s.stuXuehao,s.stuMobile,s.stuCheckstate,s.stuIsinschool,c.className) from Student s left join ClassModel c on s.classId = c.classId where s.stuCheckstate = true")
    Page<StudentVo> findStudentsByStuCheckstate(Pageable pageable);

    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentVo(s.id,s.stuUname,s.stuPassword,s.stuName,s.stuXuehao,s.stuMobile,s.stuCheckstate,s.stuIsinschool,c.className) from Student s left join ClassModel c on s.classId = c.classId where s.stuCheckstate = true and s.stuXuehao = ?1")
    StudentVo findStudentsByStuXuehao(String xuehao);

    @Query("select new com.coolwen.experimentplatform.model.DTO.StudentListDTO(s.id,s.stuUname,s.stuPassword,s.stuName,s.stuXuehao,s.stuMobile,s.stuCheckstate,s.stuIsinschool,c.className,c.classId) from Student s left join ClassModel c on s.classId = c.classId where s.stuCheckstate = true and s.id = ?1")
    StudentListDTO findStudentsById(int id);

    @Query("select s from Student s where s.stuCheckstate = false ")
    Page<Student> findToBeReviewedStudent(Pageable pageable);

    @Query("select s from Student s where s.stuCheckstate = false and s.stuXuehao = ?1 ")
    Student findStudentByStuXuehao(String xuehao);

    @Query("select s from Student s where s.stuCheckstate = true and s.stuXuehao = ?1 ")
    Student findclassStudentByStuXuehao(String xuehao);

    List<Student> findStudentByClassId(int class_id);

//    Page<Student> pageStudentByClassId(int class_id);

    @Query("select s from Student s where s.stuUname = ?1 ")
    Student findAllByStuUname(String stuUname);


//<<<<<<< Updated upstream
    @Query(value = "select stu_uname from t_student where id = ?",nativeQuery = true)
    String findStudentname(int a);
//=======
////    List<TreportGradeDto> ListStudentDto();
//
//>>>>>>> Stashed changes

    @Query("select s from ClassModel c, Student s where  c.classId = s.classId and c.classIscurrent = false ")
    List<Student> findStudentByNotClassId();

    @Query("select s from ClassModel c, Student s where  c.classId = s.classId and c.classIscurrent = false ")
    Page<Student> findCurrentKaoheAllStudent(Specification classId, Pageable pager);

    @Query("select s from ClassModel c, Student s where  s.classId>0 and c.classId = s.classId and c.classIscurrent = false and s.id = ?1")
    List<Student> findStudentIsCurrentkaoheByStuid(int stuId);
}
