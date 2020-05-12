package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:30
 */
@Service
public class ModuleTestQuestServiceImpl implements ModuleTestQuestService {

    @Autowired
    private ModuleTestQuestRepository questRepository;

    @Override
    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest) {
        questRepository.save(moduleTestQuest);
    }

    @Override
    public void deleteQuest(int questId) {
        questRepository.deleteById(questId);
    }

    @Override
    public void updateQuest(int questId) {
        questRepository.findById(questId);
    }


    @Override
    public List<ModuleTestQuest> load(String questDescribe) {
        return questRepository.findAllByQuestDescribe(questDescribe);
    }

    @Override
    public ModuleTestQuest update(int questId) {
        ModuleTestQuest quest = new ModuleTestQuest();
        quest = questRepository.findQuestByQuestId(questId);
        return quest;
    }

    @Override
    public List<ModuleTestQuest> loadAll() {
        return questRepository.findAll();
    }
}
