package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestAnswerStuRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artell
 * @version 2020/5/18 18:12
 */

@Service
public class ModuleTestAnswerStuServiceImpl implements ModuleTestAnswerStuService {

    @Autowired
    public ModuleTestAnswerStuRepository moduleTestAnswerStuRepository;

    @Override
    public void add(ModuleTestAnswerStu mtastu) {
        moduleTestAnswerStuRepository.save(mtastu);
    }

    @Override
    public List<ModuleTestAnswerStu> findAllModuleTestAnswerStuByStuidAndQuestId(int Stuid, int QuestId) {
        return moduleTestAnswerStuRepository.findModuleTestAnswerStusByStu_idAndQuest_id(Stuid,QuestId);
    }

    @Override
    public void deleteModuleTestAnswerStuByStuId(int id) {
        List<ModuleTestAnswerStu> list = moduleTestAnswerStuRepository.findModuleTestAnswerStuByStu_id(id);
        moduleTestAnswerStuRepository.deleteAll(list);
    }

    @Override
    public ModuleTestAnswerStu findModuleTestAnswerStuByStu_idAndQuest_id(int stuid, int questid) {
        return moduleTestAnswerStuRepository.findModuleTestAnswerStuByStu_idAndQuest_id(stuid,questid);
    }

    @Override
    public void deleteByQuestId(int questid) {
        List<ModuleTestAnswerStu> list = moduleTestAnswerStuRepository.findModuleTestAnswerStuByQuest_id(questid);
        moduleTestAnswerStuRepository.deleteAll(list);
    }

    @Override
    public List<ModuleTestAnswerStu> findStudentAnswbyStuidAndMid(int stuId, Integer mid) {
        return moduleTestAnswerStuRepository.findStudentAnswbyStuidAndMid(stuId ,mid);
    }

    @Override
    public ModuleTestAnswerStu findByQuest_id(int questid) {
        return moduleTestAnswerStuRepository.findByQuest_id(questid);
    }
}
