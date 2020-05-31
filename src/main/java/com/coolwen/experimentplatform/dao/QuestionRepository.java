package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 写的查询语句
 *  @author yellow
 */

public interface QuestionRepository extends JpaRepository<Question, Integer> {

//    查询问题内容
    @Query(value = "select content from t_question where id = ?", nativeQuery = true)
    public String findByquestioncontent(int id);

//    查询问题全部属性
    @Query(value = "select * from t_question where id = ?", nativeQuery = true)
    public Question findById(int id);

//    通过登录的seesion的用户名查出id等DTO里内容
    @Query(value = "select  new com.coolwen.experimentplatform.model.DTO.QuestionStudentDto(q.id,q.sid,q.content,q.dic_datetime,s.stuUname,q.isreply) from Question q,Student s where q.sid=s.id order by q.isreply,q.dic_datetime desc ")
    public Page<QuestionStudentDto> findAndUname(Pageable pageable);

//    查出提问学生姓名
    @Query(value = "select stu_uname from t_student where id = ？", nativeQuery = true)
    public String findQuestionUname(int id);

//    当老师或学生回复时修改是否回复
    @Modifying
    @Transactional
    @Query(value = "update t_question set isreply = ? where id = ?", nativeQuery = true)
    void setIsreply(boolean b);
}
