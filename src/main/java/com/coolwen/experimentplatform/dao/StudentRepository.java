package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.StudentTestScoreDTO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface StudentRepository extends BaseRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

    Student findAllById(int id);

    @Query("select new com.coolwen.experimentplatform.model.StudentTestScoreDTO" +
            "(st.id, st.stuName, st.classId, expm.m_name, khms.mTestScore, khms.mTeststate)" +
            "from Student st ,KaoHeModelScore khms ,ExpModel expm ,KaoheModel khm " +
            "where st.id=khms.stuId and khms.tKaohemodleId=khm.id and khm.m_id = expm.m_id ")
    public List<StudentTestScoreDTO> listStudentMTestAnswerDTO();
    //    and st.id=?1




    Student findAllByStuUname(String stuUname);
}
