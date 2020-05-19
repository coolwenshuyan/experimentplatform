package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    //    @Query("select t from t_question t where t.id = ?1")
    @Query(value = "select content from t_question where id = ?", nativeQuery = true)
    public String findByquestioncontent(int id);


//    @Query(value="select * from t_question",nativeQuery=true)
//    public Question getAll();

    @Query(value = "select * from t_question where id = ?", nativeQuery = true)
    public Question findById(int id);

    @Query(value = "select  new com.coolwen.experimentplatform.model.DTO.QuestionStudentDto(q.id,q.sid,q.content,q.dic_datetime,s.stuUname,q.isreply) from Question q,Student s where q.sid=s.id order by q.isreply,q.dic_datetime desc ")
    public Page<QuestionStudentDto> findAndUname(Pageable pageable);

    @Query(value = "select stu_uname from t_student where id = ？", nativeQuery = true)
    public String findQuestionUname(int id);

    @Modifying
    @Transactional
    @Query(value = "update t_question set isreply = ? where id = ?", nativeQuery = true)
    void setIsreply(boolean b);
}
