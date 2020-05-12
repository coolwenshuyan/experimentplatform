package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:08
 */
public interface ModuleTestAnswerService {

    public void addAnswerDescribe(ModuleTestAnswer moduleTestAnswer);

    public void deleteAnswer(int answerId);

    public void updateAnswerDescribe(int answerId);

    public List<ModuleTestAnswer> loadAnswerDescribe(int questId);

}
