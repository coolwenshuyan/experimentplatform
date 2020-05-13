package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Question;

import java.util.List;

public interface QuestionService {

    //添加
    void add (Question question);

    //删
    void delete(int id);

    //通过id查
    public String findByquestioncontent(int id);

    public Question findById(int id);

    //查所有
    public List<Question> getAll();
}
