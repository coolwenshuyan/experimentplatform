package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.QuestionRepository;
import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 *
 *  @author yellow
 */
@org.springframework.stereotype.Service
public class QuestionServiceImpl implements QuestionService {


//    注入
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void add(Question question) {
        questionRepository.save(question);
    }

//    删除问题
    @Override
    public void delete(int id) {
        questionRepository.deleteById(id);
    }

    //通过id查问题内容
    @Override
    public String findByquestioncontent(int id) {
        return questionRepository.findByquestioncontent(id);
    }

    //    通过id查问题所有属性
    @Override
    public Question findById(int id) {
        Question question = new Question();
        question = questionRepository.findById(id);
        return question;
    }

    //查所有问题
    @Override
    public List<Question> getAll() {
        return null;
    }

    //  通过seesion的用户名查DTO
    @Override
    public Page<QuestionStudentDto> findAndUname(Pageable pageable) {
        return questionRepository.findAndUname(pageable);
    }

    //查提问者
    @Override
    public String findQuestionUname(int id) {
        return questionRepository.findQuestionUname(id);
    }

    @Override
    public void setIsreply(boolean b) {
        questionRepository.setIsreply(b);
    }


}
