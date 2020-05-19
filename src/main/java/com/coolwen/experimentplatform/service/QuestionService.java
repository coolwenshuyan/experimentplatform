package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    //添加
    void add(Question question);

    //删
    void delete(int id);

    //通过id查
    public String findByquestioncontent(int id);

    public Question findById(int id);

    //查所有
    public List<Question> getAll();


    public Page<QuestionStudentDto> findAndUname(Pageable pageable);

    //查提问者
    public String findQuestionUname(int id);

    void setIsreply(boolean b);
}
