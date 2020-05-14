package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestAnswerRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 淮南
 * @date 2020/5/12 19:56
 */
@Service
public class ModuleTestAnswerServiceImpl implements ModuleTestAnswerService {

    @Autowired
    private ModuleTestAnswerRepository answer;


    @Override
    public void addAnswers(ModuleTestAnswer moduleTestAnswer) {
        answer.save(moduleTestAnswer);
    }

    @Override
    public void deleteAnswer(int answerId) {
        answer.deleteById(answerId);
    }

    @Override
    public ModuleTestAnswer updateAnswer(int answerId) {
        ModuleTestAnswer moduleTestAnswer = answer.findByAnswerId(answerId);
        return moduleTestAnswer;
    }

    @Override
    public List<ModuleTestAnswer> loadAnswer() {
        return answer.findAll();
    }

    @Override
    public ModuleTestAnswer findByAnswerId(int answerId) {
        ModuleTestAnswer a = answer.findByAnswerId(answerId);
        return a;
    }

    @Override
    public ModuleTestAnswer findByQuestId(int questId) {
        return answer.findAllByQuestId(questId);
    }


}
