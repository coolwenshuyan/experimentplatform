package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;

import java.util.List;
import java.util.Map;

/**
 * @author 淮南
 * @date 2020/5/12 12:08
 */
public interface ModuleTestAnswerService {

//    添加模块测试题的问题选项
    void addAnswers(ModuleTestAnswer moduleTestAnswer);

//    删除选项
    int deleteAnswer(int answerId);

//    public ModuleTestAnswer updateAnswer(int answerId);

//    public List<ModuleTestAnswer> answerList();

//    通过问题选项id找到该选项的信息
    ModuleTestAnswer findByAnswerId(int answerId);

//    通过问题id找到所以选项
    List<ModuleTestAnswer> findAllByQuestId(int questId);

//    List<ModuleTestAnswer> findAllByAnswerId(int answerI);

//    int findAnswerId(int answerId);

//    String findByAnswerDescribe(String answerDescribe);

//    通过选项id找到问题id
    int findQuestIdByAnswerId(int answerId);

//    ModuleTestAnswer findbyAnswerid(int id);

    void deleteAllAnswer(List<ModuleTestAnswer> moduleTestAnswers);
}
