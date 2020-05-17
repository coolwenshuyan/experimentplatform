package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.QuestionRepository;
import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void add(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public String findByquestioncontent(int id) {
        return questionRepository.findByquestioncontent(id);
    }

    @Override
    public Question findById(int id) {
        Question question = new Question();
        question = questionRepository.findById(id);
        return question;
    }

    @Override
    public List<Question> getAll() {
//        return questionRepository.findAll();
//        return questionRepository.findAll();
        return null;
    }

    @Override
    public Page<QuestionStudentDto> findAndUname(Pageable pageable) {
        return questionRepository.findAndUname(pageable);
    }

    @Override
    public String findQuestionUname(int id) {
        return questionRepository.findQuestionUname(id);
    }

    @Override
    public void setIsreply(boolean b) {
        questionRepository.setIsreply(b);
    }


}
