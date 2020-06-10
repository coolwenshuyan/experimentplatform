package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 *  @author yellow
 */
public interface QuestionService {

    //添加问题
    void add(Question question);

    //删问题
    void delete(int id);

    //通过id查问题内容
    public String findByquestioncontent(int id);

//    通过id查问题所有属性
    public Question findById(int id);

    //查所有问题
    public List<Question> getAll();

//  通过seesion的用户名查DTO
    public Page<QuestionStudentDto> findAndUname(Pageable pageable);

    //查提问者
    public String findQuestionUname(int id);

    void setIsreply(boolean b);
}
