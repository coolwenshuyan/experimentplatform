package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;

import java.util.List;

/**
 * @author Artell
 * @version 2020/5/18 18:11
 */


public interface ModuleTestAnswerStuService {

    void add(ModuleTestAnswerStu mtastu);

    List<ModuleTestAnswerStu> findAllModuleTestAnswerStuByStuidAndQuestId(int Stuid,int QuestId);

    void deleteModuleTestAnswerStuByStuId(int id);
}
