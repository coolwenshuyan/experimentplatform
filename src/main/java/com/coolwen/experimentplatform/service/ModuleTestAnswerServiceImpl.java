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
    public int deleteAnswer(int answerId) {
        System.out.println("service里面的+++++++++"+answerId);
        return answer.deleteByAnswerId(answerId);
    }

    @Override
    public ModuleTestAnswer updateAnswer(int answerId) {
        ModuleTestAnswer moduleTestAnswer = answer.findByAnswerId(answerId);
        return moduleTestAnswer;
    }

    @Override
    public List<ModuleTestAnswer> answerList() {
        return answer.findAll();
    }

    @Override
    public ModuleTestAnswer findByAnswerId(int answerId) {
        ModuleTestAnswer a = answer.findByAnswerId(answerId);
        return a;
    }

    @Override
    public List<ModuleTestAnswer> findAllByQuestId(int questId) {
        return answer.findAllByQuestId(questId);
    }

    @Override
    public List<ModuleTestAnswer> findAllByAnswerId(int answerId) {
        return answer.findAllByAnswerId(answerId);
    }

    @Override
    public int findAnswerId(int answerId) {
        return answer.findByAId(answerId);
    }

    @Override
    public String findByAnswerDescribe(String answerDescribe) {
        return answer.findByAnswerDescribe(answerDescribe);
    }

    @Override
    public int findQuestIdByAnswerId(int answerId) {
        ModuleTestAnswer a =answer.findByAnswerId(answerId);

        return a.getQuestId();
    }

    @Override
    public ModuleTestAnswer findbyAnswerid(int id) {
        return findByAnswerId(id);
    }

}
