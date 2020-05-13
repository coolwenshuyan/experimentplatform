package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.QuestionDao;
import com.coolwen.experimentplatform.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public void add(Question question) {
        questionDao.save(question);
    }

    @Override
    public void delete(int id) {
        questionDao.deleteById(id);
    }

    @Override
    public String findByquestioncontent(int id) {
        return questionDao.findByquestioncontent(id);
    }

    @Override
    public Question findById(int id) {
        Question question = new Question();
        question = questionDao.findById(id);
        return question;
    }

    @Override
    public List<Question> getAll() {
//        return questionRepository.findAll();
//        return questionDao.findAll();
        return null;
    }
}
