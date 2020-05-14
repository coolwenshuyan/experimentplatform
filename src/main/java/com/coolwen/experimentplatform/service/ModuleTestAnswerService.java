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

    public void addAnswers(ModuleTestAnswer moduleTestAnswer);

    public void deleteAnswer(int answerId);

    public ModuleTestAnswer updateAnswer(int answerId);

    public List<ModuleTestAnswer> loadAnswer();

    ModuleTestAnswer findByAnswerId(int answerId);

    ModuleTestAnswer findByQuestId(int questId);
}
