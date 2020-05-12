package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestAnswerRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 19:56
 */
@Service
public class ModuleTestAnswerServiceImpl implements ModuleTestAnswerService {

    @Autowired
    private ModuleTestAnswerRepository answer;

    @Override
    public void addAnswerDescribe(ModuleTestAnswer moduleTestAnswer) {
        answer.save(moduleTestAnswer);
    }

    @Override
    public void deleteAnswer(int answerId) {
        answer.deleteById(answerId);
    }

    @Override
    public void updateAnswerDescribe(int answerId) {

        answer.findById(answerId);
    }

    @Override
    public List<ModuleTestAnswer> loadAnswerDescribe(int questId) {
        return answer.findAll();
    }

}
